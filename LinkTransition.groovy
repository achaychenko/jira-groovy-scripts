import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.link.IssueLinkManager
import com.opensymphony.workflow.InvalidInputException

CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()
CustomField resultRasm = customFieldManager.getCustomFieldObject(11329)

def value = issue.getCustomFieldValue(resultRasm).get("1").toString()

request = webwork.action.ActionContext.getRequest()
IssueLinkManager issueLinkManager = ComponentAccessor.getIssueLinkManager()

oldLink = issueLinkManager.getInwardLinks(issue.getId())

if (oldLink.isEmpty() && request){
    linkedIssue = request.getParameter('issuelinks-issues')
    if(!linkedIssue){
        if(	value == "Баланс скорректирован" ||
                value == "Были технические неполадки, либо плановые работы, которые на данный момент устранены" ||
                value == "В разработке, передано в соответствующий отдел" ||
                value == "Профиль и баланс скорректированы" ||
                value == "Профиль скорректирован" ||
                value == "Профиль скорректирован, просьба повторить попытку" ||
                value == "В указанный период не отмечено SMS, необходимо проверить настройки на телефоне (номер SMSC), либо поменять SIM карту" ||
                value == "Во время пополнения были технические неполадки, либо плановые работы, кредит выдался с задержкой, а долг снимется при следующем платеже"
        ){
            invalidInputException = new InvalidInputException("Введи связанную задачу!!")
        }
    }
}
