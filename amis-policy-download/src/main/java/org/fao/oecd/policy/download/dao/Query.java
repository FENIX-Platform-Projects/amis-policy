package org.fao.oecd.policy.download.dao;

public enum Query {
    Timeseries_download(
            "WITH timeseries AS (SELECT to_number(to_char(generate_series('<<from>>'::TIMESTAMP, '<<to>>'::TIMESTAMP, '1 month'),'YYYYMM'),'999999') AS MONTH)\n" +
                    "SELECT final.policytype,\n" +
                    "       final.month,\n" +
                    "       final.country\n" +
                    "FROM\n" +
                    "  (SELECT *\n" +
                    "   FROM timeseries\n" +
                    "   LEFT JOIN\n" +
                    "     (SELECT m.policytype_code AS policytype,\n" +
                    "             m.country_code AS country,\n" +
                    "             to_number(to_char(start_date,'YYYYMM'), '999999') AS start_date,\n" +
                    "             to_number(to_char(end_date,'YYYYMM'), '999999') AS end_date\n" +
                    "      FROM\n" +
                    "\t(\t SELECT cpl_id,policytype_code,country_code\n" +
                    "\t\t FROM cpl\n" +
                    "\t\t WHERE policytype_code IN (<<policyType>>) AND commodityclass_code IN (<<commodityClass>>)\n" +
                    "\t\t GROUP BY cpl_id,policytype_code,country_code\n" +
                    "         ) m, policy\n" +
                    "      WHERE m.cpl_id = policy.cpl_id\n" +
                    "      GROUP BY policytype, country, start_date, end_date ) h\n" +
                    "      ON ((h.end_date IS NULL AND MONTH >= h.start_date) OR (MONTH BETWEEN h.start_date AND h.end_date))\n" +
                    "  ) final\n" +
                    "GROUP BY final.policytype,\n" +
                    "         final.country,\n" +
                    "         final.month"
    ),

    Timeseries_Detailed_download(
            "WITH timeseries AS (SELECT to_number(to_char(generate_series('<<from>>'::TIMESTAMP, '<<to>>'::TIMESTAMP, '1 month'),'YYYYMM'),'999999') AS MONTH)\n" +
                    "SELECT final.policytype,\n" +
                    "       final.policymeasure,\n" +
                    "       final.month,\n" +
                    "       final.country\n" +
                    "FROM\n" +
                    "  (SELECT *\n" +
                    "   FROM timeseries\n" +
                    "   LEFT JOIN\n" +
                    "     (SELECT m.policytype_code AS policytype,\n" +
                    "             m.policymeasure_code AS policymeasure,\n" +
                    "             m.country_code AS country,\n" +
                    "             to_number(to_char(start_date,'YYYYMM'), '999999') AS start_date,\n" +
                    "             to_number(to_char(end_date,'YYYYMM'), '999999') AS end_date\n" +
                    "      FROM\n" +
                    "\t(\t SELECT cpl_id,policytype_code,policymeasure_code,country_code\n" +
                    "\t\t FROM cpl\n" +
                    "\t\t WHERE policytype_code IN (<<policyType>>) AND commodityclass_code IN (<<commodityClass>>)\n" +
                    "\t\t GROUP BY cpl_id,policytype_code,policymeasure_code,country_code\n" +
                    "         ) m, policy\n" +
                    "      WHERE m.cpl_id = policy.cpl_id\n" +
                    "      GROUP BY policytype, policymeasure, country, start_date, end_date ) h\n" +
                    "      ON ((h.end_date IS NULL AND MONTH >= h.start_date) OR (MONTH BETWEEN h.start_date AND h.end_date))\n" +
                    "  ) final\n" +
                    "GROUP BY final.policytype,\n" +
                    "         final.policymeasure,\n" +
                    "         final.country,\n" +
                    "         final.month"
    );


    private String query;
    Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return query;
    }
}
