package com.server.client.mapper;

import com.server.client.pojo.ClientSourceExample;
import com.server.client.pojo.ClientSourceExample.Criteria;
import com.server.client.pojo.ClientSourceExample.Criterion;
import com.server.common.ClientSource;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class ClientSourceSqlProvider {

    public String countByExample(ClientSourceExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("client_source");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ClientSourceExample example) {
        BEGIN();
        DELETE_FROM("client_source");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ClientSource record) {
        BEGIN();
        INSERT_INTO("client_source");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getAppId() != null) {
            VALUES("app_id", "#{appId,jdbcType=VARCHAR}");
        }

        if (record.getAppName() != null) {
            VALUES("app_name", "#{appName,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    public String selectByExample(ClientSourceExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("app_id");
        SELECT("app_name");
        FROM("client_source");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ClientSource record = (ClientSource) parameter.get("record");
        ClientSourceExample example = (ClientSourceExample) parameter.get("example");

        BEGIN();
        UPDATE("client_source");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getAppId() != null) {
            SET("app_id = #{record.appId,jdbcType=VARCHAR}");
        }

        if (record.getAppName() != null) {
            SET("app_name = #{record.appName,jdbcType=VARCHAR}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("client_source");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("app_id = #{record.appId,jdbcType=VARCHAR}");
        SET("app_name = #{record.appName,jdbcType=VARCHAR}");

        ClientSourceExample example = (ClientSourceExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(ClientSource record) {
        BEGIN();
        UPDATE("client_source");

        if (record.getAppId() != null) {
            SET("app_id = #{appId,jdbcType=VARCHAR}");
        }

        if (record.getAppName() != null) {
            SET("app_name = #{appName,jdbcType=VARCHAR}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(ClientSourceExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}
