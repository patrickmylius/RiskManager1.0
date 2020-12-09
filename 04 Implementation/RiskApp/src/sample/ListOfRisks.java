package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListOfRisks {

    static ArrayList<Risk> riskList = new ArrayList<>();


    static void sortRiskList(){

        riskList.sort(Comparator.comparing(o -> o.probability * o.consequenceValue));
        Collections.reverse(riskList);

    }

    static void setPriority(){

        for (int i = 0; i < riskList.size(); i++) {

            riskList.get(i).priority = i + 1;

        }

    }

}
