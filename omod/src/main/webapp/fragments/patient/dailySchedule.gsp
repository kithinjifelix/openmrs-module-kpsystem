<%
	ui.includeJavascript("kenyaemr", "controllers/patient.js")

	def heading = "scheduled for "
	if (isToday)
		heading += "Today"
	else if (isTomorrow)
		heading += "Tomorrow"
	else if (isYesterday)
		heading = "scheduled for appointments Yesterday"
	else if (isFuture)
	    heading += kenyaui.formatDate(date)
	else
        heading ="scheduled for appointments on "+ kenyaui.formatDate(date)
%>

<div class="ke-panel-frame" ng-controller="DailySchedule" ng-init="init('${ currentApp.id }', '${ kenyaui.formatDateParam(date) }', '${ config.pageProvider }', '${ config.page }')">
	<div class="ke-panel-heading">{{ scheduled.length }} Clients ${ heading }</div>
	<div class="ke-panel-content">
		<div class="ke-stack-item ke-navigable" ng-repeat="patient in scheduled" ng-click="onResultClick(patient)">
			${ ui.includeFragment("kenyaemr", "patient/result.full") }
		</div>
		<div ng-if="scheduled.length == 0" style="text-align: center; font-style: italic">None</div>
	</div>
</div>