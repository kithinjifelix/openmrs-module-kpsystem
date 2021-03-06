/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemr.converter.simplifier;

import org.openmrs.module.kenyacore.report.IndicatorReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyaui.simplifier.AbstractSimplifier;
import org.openmrs.ui.framework.SimpleObject;
import org.springframework.stereotype.Component;

/**
 * Converts a report descriptor to a simple object
 */
@Component
public class ReportDescriptorSimplifier extends AbstractSimplifier<ReportDescriptor> {

	/**
	 * @see AbstractSimplifier#simplify(Object)
	 */
	@Override
	protected SimpleObject simplify(ReportDescriptor report) {
		return SimpleObject.create(
				"id", report.getId(),
				"name", report.getName(),
				"description", report.getDescription(),
				"isIndicator", report instanceof IndicatorReportDescriptor,
				"definitionUuid", report.getTargetUuid()
		);
	}
}