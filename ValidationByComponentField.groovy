import com.opensymphony.workflow.InvalidInputException

def componentName= issue.getComponentObjects()

if (!(componentName.size() == 1 & (componentName.name.contains("ДОК") | componentName.name.contains("КД")))){
    invalidInputException = new InvalidInputException("Поле компонент должнен быть ДОК или КД")
}