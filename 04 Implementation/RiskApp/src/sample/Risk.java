package sample;

public class Risk extends ListOfRisks{

    int probability;
    int revisedProbability; //
    int consequenceValue;
    int revisedConsequenceValue; //
    int priority;
    int finalPriority;

    String caseExplanation;
    String responseStrategy;

    Risk(String caseExp, int prob, int consVal){
        this.caseExplanation = caseExp;
        this.probability = prob;
        this.consequenceValue = consVal;
    }

    Risk(int prio, String caseExp, int prob, int consVal){
        this.priority = prio;
        this.caseExplanation = caseExp;
        this.probability = prob;
        this.consequenceValue = consVal;
    }


    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public int getRevisedProbability() {
        return revisedProbability;
    }

    public void setRevisedProbability(int revisedProbability) {
        this.revisedProbability = revisedProbability;
    }

    public int getConsequenceValue() {
        return consequenceValue;
    }

    public void setConsequenceValue(int consequenceValue) {
        this.consequenceValue = consequenceValue;
    }

    public int getRevisedConsequenceValue() {
        return revisedConsequenceValue;
    }

    public void setRevisedConsequenceValue(int revisedConsequenceValue) {
        this.revisedConsequenceValue = revisedConsequenceValue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getFinalPriority() {
        return finalPriority;
    }

    public void setFinalPriority(int finalPriority) {
        this.finalPriority = finalPriority;
    }

    public String getCaseExplanation() {
        return caseExplanation;
    }

    public void setCaseExplanation(String caseExplanation) {
        this.caseExplanation = caseExplanation;
    }


    public String getResponseStrategy() {
        return responseStrategy;
    }

    public void setResponseStrategy(String responseStrategy) {
        this.responseStrategy = responseStrategy;
    }
}
