package org.openmrs.module.kenyaemr.reporting.data.converter.definition.evaluator.hei;

import org.openmrs.annotation.Handler;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.hei.HEIPCRConfirmatoryResultCollectionDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.hei.HEIPCRConfirmatoryResultDateDataDefinition;
import org.openmrs.module.reporting.data.person.EvaluatedPersonData;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.data.person.evaluator.PersonDataEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.evaluation.querybuilder.SqlQueryBuilder;
import org.openmrs.module.reporting.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Evaluates a PersonDataDefinition
 */
@Handler(supports= HEIPCRConfirmatoryResultCollectionDateDataDefinition.class, order=50)
public class HEIPCRConfirmatoryResultCollectionDateDataEvaluator implements PersonDataEvaluator {

    @Autowired
    private EvaluationService evaluationService;

    public EvaluatedPersonData evaluate(PersonDataDefinition definition, EvaluationContext context) throws EvaluationException {
        EvaluatedPersonData c = new EvaluatedPersonData(definition, context);

        String qry = "select\n" +
                "  f.patient_id,\n" +
                "  f.dna_pcr_results_date as twelve_months_results_collection_date\n" +
                "from  kenyaemr_etl.etl_hei_follow_up_visit f\n" +
                "  INNER JOIN kenyaemr_etl.etl_patient_demographics d ON\n" +
                "                                          f.patient_id = d.patient_id\n" +
                "WHERE f.dna_pcr_result = 703\n" +
                "GROUP BY f.patient_id";

        SqlQueryBuilder queryBuilder = new SqlQueryBuilder();
        queryBuilder.append(qry);
        Map<Integer, Object> data = evaluationService.evaluateToMap(queryBuilder, Integer.class, Object.class, context);
        c.setData(data);
        return c;
    }
}