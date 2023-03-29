
import java.util.*;
import java.util.stream.*;

public class Age {

    public static void main(String[] args) {
        
        
        List<Integer> passengerAges = Arrays.asList(5, 12, 18, 25, 33, 38, 44, 51, 62);
        
        
        int[] ageGroupBoundaries = {0, 10, 20, 30, 40, 50};
        
        
        Map<String, Long> ageGroupPassengerCount = passengerAges.stream()
            .map(age -> getAgeGroupLabel(getAgeGroup(age, ageGroupBoundaries), ageGroupBoundaries))
            .collect(Collectors.groupingBy(ageGroup -> ageGroup, Collectors.counting()));
        
        
        ageGroupPassengerCount.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        });
        
        
        String maxPassengersAgeGroupLabel = ageGroupPassengerCount.entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .get()
            .getKey();
        System.out.println("Age group with highest passengers: " + maxPassengersAgeGroupLabel);
        
        
        String minPassengersAgeGroupLabel = ageGroupPassengerCount.entrySet().stream()
            .min(Comparator.comparing(Map.Entry::getValue))
            .get()
            .getKey();
        System.out.println("Age group with least passengers: " + minPassengersAgeGroupLabel);
    }
    
    
    private static int getAgeGroup(int age, int[] ageGroupBoundaries) {
        int ageGroup = ageGroupBoundaries.length;
        for (int i = 0; i < ageGroupBoundaries.length; i++) {
            if (age < ageGroupBoundaries[i]) {
                ageGroup = i;
                break;
            }
        }
        return ageGroup;
    }
    
    
    private static String getAgeGroupLabel(int ageGroupIndex, int[] ageGroupBoundaries) {
        if (ageGroupIndex == 0) {
            return "0 - " + ageGroupBoundaries[0];
        } else if (ageGroupIndex == ageGroupBoundaries.length) {
            return ageGroupBoundaries[ageGroupBoundaries.length - 1] + "+";
        } else {
            return (ageGroupBoundaries[ageGroupIndex - 1] + 1) + " - " + ageGroupBoundaries[ageGroupIndex];
        }
    }
}
/*
 Output
 50+ : 2
31 - 40 : 2
1 - 10 : 1
11 - 20 : 2
41 - 50 : 1
21 - 30 : 1
Age group with highest passengers: 50+
Age group with least passengers: 1 - 10
 */
