import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.IssueImpl
import com.atlassian.jira.issue.IssueManager
import groovyx.net.http.HTTPBuilder

def cFieldNum
def issueType = issue.issueType.name
def projectKey = issue.project.key

switch(projectKey){
    case "MINT":
        if(issueType == "Некорректная работа услуги"){cFieldNum = 11213}
        break
    case "FG":
        if(issueType == "Проблема активации услуги" || issueType == "Тарификация мобильного интернета" || issueType == "Массовая проблема продукта"){cFieldNum = 11213}
        break
    case "SIM":
        if(issueType == "Проблема активации" || issueType == "Ошибка регистрации SIM–карты(Проблема после восстановления)"){cFieldNum = 11213}
        break
    case "LIK":
        if(issueType == "Некорректная работа"){cFieldNum = 11213}
        break
    case "VOIC":
        if(issueType == "Не проходят входящие вызовы" || issueType == "Ekscliuzivnyi FRAUD/Closed Onoi"){cFieldNum = 11213}
        break
    case "NS":
        if(issueType == "Проблема активации услуги" || issueType == "Тарификация мобильного интернета" || issueType == "Массовая проблема продукта"){cFieldNum = 11213}
        break
    case "PRPR":
        if(issueType == "Прямые продажи- проблемы"){cFieldNum = 11213}
        break
    case "PROM":
        if(issueType == "Проблема активации Promo - услуги или SIM - карт" || issueType == "Активация сим карт" || issueType == "Подключение услуг и USSD команды"){cFieldNum = 11213}
        break
    default:
        cFieldNum = null
        break
}
if(cFieldNum != null){

    def cField = ComponentAccessor.getCustomFieldManager().getCustomFieldObject(cFieldNum)
    CfValue = issue.getCustomFieldValue(cField)
}else{
    CfValue = issue.summary
}


HTTPBuilder http = new HTTPBuilder('http://172.27.129.175:8887')
String greeting = "/getSubscriberArpuByMsisdn/" + CfValue

def html = http.get(path : greeting){ resp, reader ->
    def(a, b) = reader.text.tokenize( '.' )

    double value = Double.parseDouble(a)
    return Math.abs(value)
}