package com.esg.horta.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RelatorioRepository {

  private final JdbcTemplate jdbc;

  public RelatorioRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Map<String,Object>> doacoesMensais(int ano, int mes) {
    String sql = """
        SELECT
          c.NOME AS CANTEIRO,
          NVL(SUM(d.QUANTIDADE_KG), 0) AS TOTAL_DOADO_KG
        FROM CANTEIRO c
        LEFT JOIN COLHEITA col ON col.CANTEIRO_ID = c.ID
        LEFT JOIN DOACAO d ON d.COLHEITA_ID = col.ID
        WHERE EXTRACT(YEAR FROM col.DATA_COLHEITA) = ?
          AND EXTRACT(MONTH FROM col.DATA_COLHEITA) = ?
        GROUP BY c.NOME
        ORDER BY c.NOME
      """;
    return jdbc.queryForList(sql, ano, mes);
  }

  public Map<String, Object> indicadores(int ano, int mes) {
    String sql = """
      SELECT
        NVL(SUM(d.QUANTIDADE_KG),0) AS total_doado_kg,
        NVL(SUM(ca.META_DOACAO_KG),0) AS meta_total_kg
      FROM CANTEIRO ca
      LEFT JOIN COLHEITA col ON col.CANTEIRO_ID = ca.ID
      LEFT JOIN DOACAO d ON d.COLHEITA_ID = col.ID
      WHERE EXTRACT(YEAR FROM col.DATA_COLHEITA) = ?
        AND EXTRACT(MONTH FROM col.DATA_COLHEITA) = ?
    """;
    Map<String,Object> row = jdbc.queryForMap(sql, ano, mes);
    double doado = ((Number)row.get("TOTAL_DOADO_KG")).doubleValue();
    double meta = ((Number)row.get("META_TOTAL_KG")).doubleValue();
    double perc = (meta > 0) ? Math.round((doado/meta)*10000.0)/100.0 : 0.0;
    Map<String,Object> out = new LinkedHashMap<>();
    out.put("total_doado_kg", doado);
    out.put("meta_total_kg", meta);
    out.put("percentual_meta_cumprida", perc);
    return out;
  }
}

