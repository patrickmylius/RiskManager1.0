package sample;

public class Risk extends ListOfRisks{

    int probability;
    int revisedProbability;
    int consequenceValue;
    int revisedConsequenceValue;
    int priority;
    int finalPriority;

    String caseExplanation;
    String consequence;


    Risk(String caseExp, int prob, int consVal){
        this.caseExplanation = caseExp;
        this.probability = prob;
        this.consequenceValue = consVal;
    }

}
