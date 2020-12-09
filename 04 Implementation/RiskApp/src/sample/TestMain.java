package sample;

public class TestMain extends ListOfRisks {

    public static void main(String[] args) {

        //login()

        // createNewProject()

        Risk risk1 = new Risk("bliver kørt over", 3,10);
        Risk risk2 = new Risk("corona",7,10);
        Risk risk3 = new Risk("bliver kvalt af en kartoffel", 4, 10);
        Risk risk4 = new Risk("sover over mig", 8, 2);

        riskList.add(risk1);
        riskList.add(risk2);
        riskList.add(risk3);
        riskList.add(risk4);

        sortRiskList();

        setPriority();

        for (Risk risk : riskList) {
            System.out.println(risk.caseExplanation);
            System.out.println("prioritet: " + risk.priority + "\n");
        }

    }

}
