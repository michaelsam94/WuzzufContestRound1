package com.company;

import java.util.*;


class Submition {
    int time;
    int problemId;
    boolean isSolved;
}

class Contestant {
    int id;
    int numOfSolvedProblem;
    int timePenelty;
    List<Integer> solvedProblems;
    Map<Integer, Integer> timeWithWrongSubmition;

    public static Comparator<Contestant> slovedProvlemsCompartor =
            (c1, c2) -> (c2.solvedProblems.size() - c1.solvedProblems.size());

    public static Comparator<Contestant> timePenltyCompartor =
            (c1, c2) -> (c1.timePenelty - c2.timePenelty);

    public static Comparator<Contestant> idCompartor =
            (c1, c2) -> (c1.id - c2.id);

    public Contestant() {
        numOfSolvedProblem = 0;
        timePenelty = 0;
        solvedProblems = new ArrayList<>();
        timeWithWrongSubmition = new HashMap<>();
    }

    public void addToSolvedProblem(int problemId, int time) {
        if (!solvedProblems.contains(problemId)) {
            solvedProblems.add(problemId);
            if (timeWithWrongSubmition.containsValue(problemId)) {
                for (Map.Entry<Integer, Integer> e : timeWithWrongSubmition.entrySet()) {
                    if (e.getKey() < time && e.getValue() == problemId) {
                        timePenelty += 20;
                    }
                }
            }
            timePenelty += time;
            numOfSolvedProblem++;
        }
    }

    public void addToWrongProblem(int problmeId, int time) {
        timeWithWrongSubmition.put(time, problmeId);
    }

    @Override
    public String toString() {
        return "Contestant{" +
                "id=" + id +
                ", numOfSolvedProblem=" + numOfSolvedProblem +
                ", timePenelty=" + timePenelty +
                ", solvedProblems=" + solvedProblems +
                ", timeWithWrongSubmition=" + timeWithWrongSubmition +
                '}';
    }
}

public class P480 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t;
        int numOfContestants, numOfProplems, numOfSubmition;


        List<Contestant> contestants;


        t = in.nextInt();

        for (int i = 0; i < t; i++) {
            numOfContestants = in.nextInt();
            numOfProplems = in.nextInt();
            numOfSubmition = in.nextInt();

            contestants = new ArrayList<>();

            for (int j=1; j <= numOfContestants;j++){
                Contestant contestant = new Contestant();
                contestant.id = j;
                contestants.add(contestant);
            }


            for (int j = 0; j < numOfSubmition; j++) {
                int contestatntId;
                Submition submition = new Submition();


                submition.time = in.nextInt();
                contestatntId = in.nextInt();
                submition.problemId = in.nextInt();
                submition.isSolved = in.nextInt() == 0 ? false : true;

                Contestant contestant = getContestantById(contestatntId, contestants);



                if (submition.isSolved) {
                    contestant.addToSolvedProblem(submition.problemId, submition.time);
                } else {
                    contestant.addToWrongProblem(submition.problemId, submition.time);
                }


            }

            Collections.sort(contestants, Contestant.slovedProvlemsCompartor);

            if(contestants.size() > 0){
                int lastNumOfSolvedProblems = contestants.get(0).numOfSolvedProblem;
                int lastTimePenality = contestants.get(0).timePenelty;
                int lastId = contestants.get(0).id;

                for (int index = 1; index < contestants.size(); index++) {
                    int currentTimePenalty = contestants.get(i).timePenelty;
                    int currentSolvedProblems = contestants.get(i).numOfSolvedProblem;
                    int currentId = contestants.get(i).id;
                    if (lastNumOfSolvedProblems == currentSolvedProblems) {
                        if (lastTimePenality > currentTimePenalty) Collections.swap(contestants, index - 1, index);
                        else if (lastTimePenality == currentTimePenalty) {
                            if (lastId > currentId) Collections.swap(contestants, index - 1, index);
                        }
                    }
                    lastNumOfSolvedProblems = currentSolvedProblems;
                    lastTimePenality = currentTimePenalty;
                    lastId = currentId;
                }
            }



            for (Contestant c : contestants) {
                System.out.println(c.id + " " + c.numOfSolvedProblem + " " + c.timePenelty);
            }

        }

    }


    public static Contestant getContestantById(int id, List<Contestant> contestants) {
        for (Contestant c : contestants) {
            if (c.id == id) return c;
        }
        return null;
    }
}


