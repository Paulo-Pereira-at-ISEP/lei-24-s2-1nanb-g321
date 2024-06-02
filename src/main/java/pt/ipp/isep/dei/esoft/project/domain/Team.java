package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class Team {
    private int teamMaxSize;
    private int teamMinSize;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Collaborator> collaborators = new ArrayList<>();
    private static int nextId = 1; // Static variable to keep track of the next ID
    private final int id;


    public Team(int teamMinSize, int teamMaxSize, ArrayList<Skill> skills, int id) {
        this.teamMinSize = teamMinSize;
        this.teamMaxSize = teamMaxSize;
        this.skills = skills;
        this.id = nextId++;
    }


    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    public int getTeamMinSize() {
        return teamMinSize;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setTeamMaxSize(int teamMaxSize) {
        this.teamMaxSize = teamMaxSize;
    }

    public void setTeamMinSize(int teamMinSize) {
        this.teamMinSize = teamMinSize;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void setCollaborators(ArrayList<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    public List<Collaborator> generateTeam(List<Collaborator> listOfCollaborators) {

        Integer[] scores = new Integer[listOfCollaborators.size()];

        ArrayList<Collaborator> collaboratorSorted = sortCollaboratorBySkillScore(scores, listOfCollaborators);


            for (Skill skill : skills) {

                //pesquisa no 1 vetor
                Collaborator current = hasSkill(collaborators, skill);

                //procura no vetor da equipa
                //se encontra, remove a skill do employee encontrado
                //senao
                //procura no vetor dos employees ordenado
                //se encontrado adiciona a equipa
                if (current != null) {
                    var skills = current.getSkills();
                    skills.remove(skill);
                    current.setSkills(skills);


                } else {
                    //pesquisa no 2 vetor

                    current = hasSkill(collaboratorSorted, skill);
                    if (current != null) {
                        var skills = current.getSkills();
                        skills.remove(skill);
                        current.setSkills(skills);
                        collaborators.add(current);
                   } else {
                        System.out.println("Team not created! ");
                    }
                }

                int dif = teamMinSize - collaborators.size();
                int i = 0;
                while (dif != 0 && i < skills.size()) {
                    Skill skill1 = skills.get(i);
                    //percorre as skills
                    //percorre os verifica se os employees tem a skill
                    //se o employee devolvido ja estiver na equipa
                    //remove o emplyee da lista de employees
                    //procura novamente
                    //quando encontrado
                    //calcula o dif novamente
                    Collaborator collaboratorToChoose = hasSkill(collaboratorSorted, skill1);

                    if (collaboratorToChoose != null && collaborators.contains(collaboratorToChoose)) {
                        collaboratorSorted.remove(collaboratorToChoose);
                    } else {
                        if (collaboratorToChoose != null)
                            collaborators.add(collaboratorToChoose);

                    }
                    i++;
                    dif = teamMinSize - collaborators.size();
                }
            }
        for (Collaborator collaborator : collaborators) {
            collaborator.setHasTeam(true);
        }
        return collaborators;
    }

    public List<Collaborator> generateSecondTeam(List<Collaborator> listOfCollaborators) {


        for (Skill skill : skills) {

            //pesquisa no 1 vetor
            Collaborator current = hasSkill(collaborators, skill);

            //procura no vetor da equipa
            //se encontra, remove a skill do employee encontrado
            //senao
            //procura no vetor dos employees ordenado
            //se encontrado adiciona a equipa
            if (current != null) {
                var skills = current.getSkills();
                skills.remove(skill);
                current.setSkills(skills);


            } else {
                //pesquisa no 2 vetor

                current = hasSkill((ArrayList<Collaborator>) listOfCollaborators, skill);
                if (current != null) {
                    var skills = current.getSkills();
                    skills.remove(skill);
                    current.setSkills(skills);
                    collaborators.add(current);
                } else {
                    System.out.println("Team not created! ");
                }
            }

            int dif = teamMinSize - collaborators.size();
            int i = 0;
            while (dif != 0 && i < skills.size()) {
                Skill skill1 = skills.get(i);
                //percorre as skills
                //percorre os verifica se os employees tem a skill
                //se o employee devolvido ja estiver na equipa
                //remove o emplyee da lista de employees
                //procura novamente
                //quando encontrado
                //calcula o dif novamente
                Collaborator collaboratorToChoose = hasSkill((ArrayList<Collaborator>) listOfCollaborators, skill1);

                if (collaboratorToChoose != null && collaborators.contains(collaboratorToChoose)) {
                    listOfCollaborators.remove(collaboratorToChoose);
                } else {
                    if (collaboratorToChoose != null)
                        collaborators.add(collaboratorToChoose);
                }
                i++;
                dif = teamMinSize - collaborators.size();
            }
        }
        return collaborators;
    }

    public Collaborator hasSkill(ArrayList<Collaborator> collaborators, Skill skill) {

        for (Collaborator collaborator : collaborators) {

            if (collaborator.getSkills().contains(skill)) {
                return collaborator;
            }
        }

        return null;
    }
    public Collaborator hasTeam (List<Collaborator> listOfCollaborators) {
        for (Collaborator collaborator : listOfCollaborators) {
            if (!collaborator.getHasTeam()) {
                collaborator.setHasTeam(true);
                return collaborator;
            }

        }
        return null;
    }
    private ArrayList<Collaborator> sortCollaboratorBySkillScore(Integer[] scores, List<Collaborator> listOfCollaborators) {
        int scoreAux;

        ArrayList<Collaborator> collaborators1 = new ArrayList<>(listOfCollaborators);

        //cria um array de scores
        for (Collaborator collaborator : collaborators1) {
            scoreAux = 0;

            for (Skill skill : skills) {

                //verificar se o employee tem a skill
                //se sim, incrementa score no vetor scores na posição employees.indexOf(employee)
                if (collaborator.getSkills().contains(skill)) {
                    scoreAux++;
                }
            }
            scores[collaborators1.indexOf(collaborator)] = scoreAux;
        }
        for (Collaborator collaborator : collaborators1) {
            if(collaborator.getHasTeam()) {
                collaborators1.remove(collaborator);
            }
        }

        Collaborator collaboratorCopy;
        int scoreCopy;

        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores.length - 1 - i; j++) {

                if (scores[j] < scores[j + 1]) {

                    scoreCopy = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = scoreCopy;


                    collaboratorCopy = collaborators1.get(j);
                    collaborators1.set(j, collaborators1.get(j + 1));
                    collaborators1.set(j + 1, collaboratorCopy);

                }

            }
        }
        return collaborators1;
    }


    public String toString() {
        return "Team Maximum Size: " + teamMaxSize + "\nTeam Minimum Size: " + teamMinSize + "\nSkills: " + skills;
    }


    public Team clone() {
        return new Team(this.teamMinSize, this.teamMaxSize, this.skills, this.id);
    }


    public int getId() {
        return id;
    }
}
