import com.opensymphony.workflow.InvalidInputException

def componentName= issue.getComponentObjects()

if (!(componentName.size() == 1 & (componentName.name.contains("���") | componentName.name.contains("��")))){
    invalidInputException = new InvalidInputException("���� ��������� ������� ���� ��� ��� ��")
}