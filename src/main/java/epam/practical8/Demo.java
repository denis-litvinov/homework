package epam.practical8;


import java.util.List;
import epam.practical8.db.DBManager;
import epam.practical8.db.entity.Team;
import epam.practical8.db.entity.User;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }
    private static String separator = "===========================";

    public static void main(String[] args) {


        // users  ==> [ivanov]

        // teams ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

        // Part 1


        dbManager.insertUser(User.createUser("petrov"));


        dbManager.insertUser(User.createUser("obama"));


        printList(dbManager.findAllUsers());

        // users  ==> [ivanov, petrov, obama]

        System.out.println(separator);

        // Part 2

        dbManager.insertTeam(Team.createTeam("teamB"));

        dbManager.insertTeam(Team.createTeam("teamC"));

        printList(dbManager.findAllTeams());

        // teams ==> [teamA, teamB, teamC]

        System.out.println(separator);

        // Part 3

        User userPetrov = dbManager.getUser("petrov");

        System.out.println(userPetrov);
        System.out.println(userPetrov.getId());

        User userIvanov = dbManager.getUser("ivanov");

        System.out.println(userIvanov);
        System.out.println(userIvanov.getId());

        User userObama = dbManager.getUser("obama");

        System.out.println(userObama);
        System.out.println(userObama.getId());

        Team teamA = dbManager.getTeam("teamA");

        System.out.println(teamA);
        System.out.println(teamA.getId());

        Team teamB = dbManager.getTeam("teamB");

        System.out.println(teamB);
        System.out.println(teamB.getId());

        Team teamC = dbManager.getTeam("teamC");

        System.out.println(teamC);
        System.out.println(teamC.getId());

        // method setTeamsForUser must implement transaction!

        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);
        dbManager.setTeamsForUser(userPetrov, teamB, teamC);


        for (User user : dbManager.findAllUsers()) {

            printList(dbManager.getUserTeams(user));

            System.out.println("~~~~~");

        }

        // teamA

        // teamA teamB

        // teamA teamB teamC

        System.out.println(separator);

        // Part 4

        // on delete cascade!

        dbManager.deleteTeam(teamB);

        // Part 5

        teamC.setName("teamX");

        dbManager.updateTeam(teamC);

        printList(dbManager.findAllTeams());

        // teams ==> [teamB, teamX]

    }

}