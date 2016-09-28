package org.fao.oecd.policy.download.dao;

public enum Query {
    Timeseries_Biofuel_download (
            "WITH timeseries AS (SELECT to_number(to_char(generate_series('2011-01-01'::TIMESTAMP, '2014-12-31'::TIMESTAMP, '1 month'),'YYYYMM'),'999999') AS MONTH)\n" +
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
                    "\t\t WHERE policytype_code IN ('8', '10', '1', '2', '12', '9') AND commodityclass_code IN (<<commodityClass>>)\n" +
                    "\t\t GROUP BY cpl_id,policytype_code,country_code\n" +
                    "         ) m, policy\n" +
                    "      WHERE m.cpl_id = policy.cpl_id\n" +
                    "      GROUP BY policytype, country, start_date, end_date ) h\n" +
                    "      ON ((h.end_date IS NULL AND MONTH >= h.start_date) OR (MONTH BETWEEN h.start_date AND h.end_date))\n" +
                    "  ) final\n" +
                    "GROUP BY final.policytype,\n" +
                    "         final.country,\n" +
                    "         final.month\n" +
                    "ORDER BY final.policytype,\n" +
                    "         final.month,\n" +
                    "         final.country\n"
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
