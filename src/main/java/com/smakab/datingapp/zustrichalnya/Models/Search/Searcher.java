package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import javafx.scene.image.Image;
import org.javatuples.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.directoryExists;
import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.listDirectories;


public class Searcher {

    Template template;
    Person thisPerson;
    ArrayList<PersonComparable> list;
    public Searcher(Template template, UUID personUUID){
        this.template = template;
        try {
            this.thisPerson = getPerson(personUUID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPerson(UUID uuid) throws IOException {
        File generalInfoFile = new File("src\\main\\resources\\local-database\\"+uuid+"\\profile-data\\main.json");
        com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo.class);

        File personalityFile = new File("src\\main\\resources\\local-database\\"+uuid+"\\profile-data\\personality.json");
        com.smakab.datingapp.zustrichalnya.Models.Profile.Personality personality = JsonUtil.fromJsonFile(personalityFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Personality.class);

        File hobbiesFile = new File("src\\main\\resources\\local-database\\"+uuid+"\\profile-data\\hobbies.json");
        com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies hobbies = JsonUtil.fromJsonFile(hobbiesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies.class);

        File preferencesFile = new File("src\\main\\resources\\local-database\\"+uuid+"\\profile-data\\preferences.json");
        com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences preferences = JsonUtil.fromJsonFile(preferencesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences.class);

        File viewsFile = new File("src\\main\\resources\\local-database\\"+uuid+"\\profile-data\\views.json");
        com.smakab.datingapp.zustrichalnya.Models.Profile.Views views = JsonUtil.fromJsonFile(viewsFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Views.class);

        Person person = new Person(uuid, generalInfo, hobbies, personality, preferences, views);
        return person;
    }

    public ArrayList<PersonComparable> search(){
        ArrayList<Person> allPeople = loadPeopleFromFiles();
        ArrayList<PersonComparable> compatiblePeople = new ArrayList<>();
        for(Person person : allPeople){
            double compatability = this.getCompatibility(person);
            System.out.println("found "+person.uuid+" | "+compatability);
            if(compatability > 0.5) compatiblePeople.add(new PersonComparable(person, compatability));
        }
        compatiblePeople.sort(new PersonComparator());
        return compatiblePeople;
    }

    public void tempSout(){
        ArrayList<PersonComparable> souting = search();
        if (souting.isEmpty()) System.out.println("empty :£");
        for(PersonComparable personComparable : souting){
            System.out.println("Compat:" + personComparable.compatability);
        }
    }

    public ArrayList<Person> loadPeopleFromFiles(){
        ArrayList<Person> people = new ArrayList<>();
        String folderPath = "src\\main\\resources\\local-database";
        if(directoryExists(folderPath)) {
            try {
                List<String> folderNames = listDirectories(folderPath);
                for(String folderName : folderNames){
                    if(folderName.equals(this.thisPerson.uuid.toString())) continue;
                    String nextFolderPath = "src\\main\\resources\\local-database\\"+folderName+"\\profile-data";
                    if(directoryExists(nextFolderPath)){

                        File generalInfoFile = new File("src\\main\\resources\\local-database\\"+folderName+"\\profile-data\\main.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo.class);

                        File personalityFile = new File("src\\main\\resources\\local-database\\"+folderName+"\\profile-data\\personality.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.Personality personality = JsonUtil.fromJsonFile(personalityFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Personality.class);

                        File hobbiesFile = new File("src\\main\\resources\\local-database\\"+folderName+"\\profile-data\\hobbies.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies hobbies = JsonUtil.fromJsonFile(hobbiesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies.class);

                        File preferencesFile = new File("src\\main\\resources\\local-database\\"+folderName+"\\profile-data\\preferences.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences preferences = JsonUtil.fromJsonFile(preferencesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences.class);

                        File viewsFile = new File("src\\main\\resources\\local-database\\"+folderName+"\\profile-data\\views.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.Views views = JsonUtil.fromJsonFile(viewsFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Views.class);

                        Person person = new Person(UUID.fromString(folderName), generalInfo, hobbies, personality, preferences, views);
                        people.add(person);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return people;
    }
    public double getCompatibility(Person thatPerson){

        double result = 0;

        if(!thatPerson.getGeneralInfo().relationshipTypeAllProperty().contains(template.getGeneralInfo().getRelationshipType())) return Double.NEGATIVE_INFINITY;
        boolean hasRealPhoto = false;
//        for(Pair<Image, Boolean> pair : thatPerson.getGeneralInfo().getPhotos()){
//            if(pair.getValue1() == true){
//                hasRealPhoto = true;
//                break;
//            }
//        }

        double realPhotoQuantifier = 1;
        switch (template.getDetails().getRealPhoto()){
            case 0:
                break;
            case 1:
                if(!hasRealPhoto) realPhotoQuantifier = 0.9;
                break;
            case 2:
                if(!hasRealPhoto) realPhotoQuantifier = 0.75;
                break;
            case 3:
                if(!hasRealPhoto) realPhotoQuantifier = 0.5;
                break;
            case 4:
                if(!hasRealPhoto) realPhotoQuantifier = 0.25;
                break;
            case 5:
                if(!hasRealPhoto) return Double.NEGATIVE_INFINITY;
        }

        ArrayList<Integer> allowedAge = template.getDetails().getAge();
        if(thatPerson.getGeneralInfo().getAge() < allowedAge.get(0) || thatPerson.getGeneralInfo().getAge() > allowedAge.get(1)) return Double.NEGATIVE_INFINITY;

        double geoQuantifier = 1;
        if ( template.getDetails().getGeoLocation().equals("Тільки місто") ) {
            if ( !thatPerson.getGeneralInfo().getCity().equals(thisPerson.getGeneralInfo().getCity()) )
                return Double.MIN_VALUE;
        } else if ( template.getDetails().getGeoLocation().equals("Можна область") ) {
            if ( !thatPerson.getGeneralInfo().getCity().equals(thisPerson.getGeneralInfo().getCity()) ) {
                if ( !thatPerson.getGeneralInfo().getRegion().equals(thisPerson.getGeneralInfo().getRegion()) )
                    return Double.MIN_VALUE;
                else geoQuantifier = 0.5;
            }
        } else if ( template.getDetails().getGeoLocation().equals("Можна країна" )) {
            if ( !thatPerson.getGeneralInfo().getCity().equals(thisPerson.getGeneralInfo().getCity()) ) {
                if ( !thatPerson.getGeneralInfo().getRegion().equals(thisPerson.getGeneralInfo().getRegion()) )
                    geoQuantifier = 0.5;
                else geoQuantifier = 0.75;
            }
        }

        double genderQuantifier = 1;
        for(Map.Entry<String, Integer> gender : template.getDetails().getGenders().entrySet()) {
            boolean containsGender = false;
            if(thatPerson.getGeneralInfo().getGender().equals(gender.getKey())) containsGender = true;
            switch (gender.getValue()){
                case -5:
                    if(containsGender) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsGender) genderQuantifier *= 0.25;
                    break;
                case -3:
                    if(containsGender) genderQuantifier *= 0.5;
                    break;
                case -2:
                    if(containsGender) genderQuantifier *= 0.75;
                    break;
                case -1:
                    if(containsGender) genderQuantifier *= 0.9;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsGender) genderQuantifier *= 1.25;
                    break;
                case 2:
                    if(containsGender) genderQuantifier *= 1.5;
                    break;
                case 3:
                    if(containsGender) genderQuantifier *= 1.5;
                    else genderQuantifier *=0.75;
                case 4:
                    if(containsGender) genderQuantifier *= 2;
                    else genderQuantifier *=0.5;
                    break;
                case 5:
                    if(containsGender) genderQuantifier *= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        // Слайдери особистості
        for(Map.Entry<String, ArrayList<Integer>> personalitySliders : template.getPersonality().getSliders().entrySet()) {
            boolean personalityIn = false;

            Integer value = thatPerson.getPersonality().getSliders().get(personalitySliders.getKey());
            if(value == null) value = 0;

            int length = personalitySliders.getValue().get(1) - personalitySliders.getValue().get(0);
            if(value >= personalitySliders.getValue().get(0) && value <= personalitySliders.getValue().get(1)) personalityIn = true;
            if(personalityIn) result+= (11 - length);
            else result -= length;
        }

        double personalityTypeQuantifier = 1;
        for(Map.Entry<String, Integer> personalityType : template.getPersonality().getPersonalities().entrySet()){
            boolean containsPersonalityType = false;
            if(thatPerson.getPersonality().getPersonality().equals(personalityType.getKey())) containsPersonalityType = true;
            switch (personalityType.getValue()){
                case -5:
                    if(containsPersonalityType) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsPersonalityType) personalityTypeQuantifier *= 0.25;
                    break;
                case -3:
                    if(containsPersonalityType) personalityTypeQuantifier *= 0.5;
                    break;
                case -2:
                    if(containsPersonalityType) personalityTypeQuantifier *= 0.75;
                    break;
                case -1:
                    if(containsPersonalityType) personalityTypeQuantifier *= 0.9;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsPersonalityType) personalityTypeQuantifier *= 1.25;
                    break;
                case 2:
                    if(containsPersonalityType) personalityTypeQuantifier *= 1.5;
                    break;
                case 3:
                    if(containsPersonalityType) personalityTypeQuantifier *= 1.5;
                    else personalityTypeQuantifier *=0.75;
                case 4:
                    if(containsPersonalityType) personalityTypeQuantifier *= 2;
                    else personalityTypeQuantifier *=0.5;
                    break;
                case 5:
                    if(containsPersonalityType) personalityTypeQuantifier *= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        double subculturesQuantifier = 1;
        for(Map.Entry<String, Integer> subculture : template.getHobbies().getSubcultures().entrySet()){
            boolean containsSubculture = false;
            if(thatPerson.getHobbies().getSubcultures().contains(subculture.getKey())) containsSubculture = true;
            switch (subculture.getValue()){
                case -5:
                    if(containsSubculture) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsSubculture) subculturesQuantifier *= 0.5;
                    break;
                case -3:
                    if(containsSubculture) subculturesQuantifier *= 0.75;
                    break;
                case -2:
                    if(containsSubculture) subculturesQuantifier *= 0.9;
                    break;
                case -1:
                    if(containsSubculture) result-=2;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsSubculture) result+=2;
                    break;
                case 2:
                    if(containsSubculture) subculturesQuantifier *= 1.1;
                    break;
                case 3:
                    if(containsSubculture) subculturesQuantifier *= 1.5;
                    else subculturesQuantifier *=0.75;
                case 4:
                    if(containsSubculture) subculturesQuantifier *= 2;
                    else subculturesQuantifier *=0.5;
                    break;
                case 5:
                    if(containsSubculture) subculturesQuantifier *= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        for(Map.Entry<String, Integer> hobbies : template.getHobbies().getHobbies().entrySet()){
            boolean containsHobby = false;
            int importance = 0;
            if(thatPerson.getHobbies().getHobbies().keySet().contains(hobbies.getKey())){
                containsHobby = true;
                importance = thatPerson.getHobbies().getHobbies().get(hobbies.getKey());
            }
            switch (hobbies.getValue()){
                case -5:
                    if(containsHobby) return Double.NEGATIVE_INFINITY;
                    else result += 2;
                    break;
                case -4:
                    if(containsHobby) result -= importance;
                    else result += 1;
                    break;
                case -3:
                    if(containsHobby) result -= (double)importance/2;
                    break;
                case -2:
                    if(containsHobby) result -= (double)importance/4;
                    break;
                case -1:
                    if(containsHobby) result -= (double)importance/8;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsHobby) result += (double)importance/8;
                    break;
                case 2:
                    if(containsHobby) result += (double)importance/4;
                    break;
                case 3:
                    if(containsHobby) result += (double)importance/4;
                    else result-=2;;
                case 4:
                    if(containsHobby) result += (double)importance/2;
                    else result-=4;
                    break;
                case 5:
                    if(containsHobby) result += importance;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        for(Map.Entry<String, Integer> preferences : template.getPreferences().getPreferences().entrySet()){
            boolean containsPreference = false;
            int importance = 0;
            if(thatPerson.getPreferences().getPreferences().keySet().contains(preferences.getKey())){
                containsPreference = true;
                importance = thatPerson.getPreferences().getPreferences().get(preferences.getKey());
            }
            switch (preferences.getValue()){
                case -5:
                    if(containsPreference) return Double.NEGATIVE_INFINITY;
                    else result += 2;
                    break;
                case -4:
                    if(containsPreference) result -= importance;
                    else result += 1;
                    break;
                case -3:
                    if(containsPreference) result -= (double)importance/2;
                    break;
                case -2:
                    if(containsPreference) result -= (double)importance/4;
                    break;
                case -1:
                    if(containsPreference) result -= (double)importance/8;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsPreference) result += (double)importance/8;
                    break;
                case 2:
                    if(containsPreference) result += (double)importance/4;
                    break;
                case 3:
                    if(containsPreference) result += (double)importance/4;
                    else result-=2;;
                case 4:
                    if(containsPreference) result += (double)importance/2;
                    else result-=4;
                    break;
                case 5:
                    if(containsPreference) result += importance;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        double religionQuantifier = 1;
        for(Map.Entry<String, Integer> religion : template.getViews().getReligions().entrySet()){
            boolean containsReligion = false;
            if(thatPerson.getViews().getReligion().equals(religion.getKey())) containsReligion = true;
            switch (religion.getValue()){
                case -5:
                    if(containsReligion) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsReligion) religionQuantifier *= 0.25;
                    break;
                case -3:
                    if(containsReligion) religionQuantifier *= 0.5;
                    break;
                case -2:
                    if(containsReligion) religionQuantifier *= 0.75;
                    break;
                case -1:
                    if(containsReligion) religionQuantifier *= 0.9;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsReligion) religionQuantifier *= 1.25;
                    break;
                case 2:
                    if(containsReligion) religionQuantifier *= 1.5;
                    break;
                case 3:
                    if(containsReligion) religionQuantifier *= 1.5;
                    else religionQuantifier *=0.75;
                case 4:
                    if(containsReligion) religionQuantifier *= 2;
                    else religionQuantifier *=0.5;
                    break;
                case 5:
                    if(containsReligion) religionQuantifier*= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }

        double languageQuantifier = 1;
        for(Map.Entry<String, Integer> language : template.getViews().getLanguages().entrySet()){
            boolean containsLanguage = false;
            if(thatPerson.getViews().getLanguage().equals(language.getKey())) containsLanguage = true;
            switch (language.getValue()){
                case -5:
                    if(containsLanguage) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsLanguage) languageQuantifier *= 0.25;
                    break;
                case -3:
                    if(containsLanguage) languageQuantifier *= 0.5;
                    break;
                case -2:
                    if(containsLanguage) languageQuantifier *= 0.75;
                    break;
                case -1:
                    if(containsLanguage) languageQuantifier *= 0.9;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsLanguage) languageQuantifier *= 1.25;
                    break;
                case 2:
                    if(containsLanguage) languageQuantifier *= 1.5;
                    break;
                case 3:
                    if(containsLanguage) languageQuantifier *= 1.5;
                    else languageQuantifier *=0.75;
                case 4:
                    if(containsLanguage) languageQuantifier *= 2;
                    else languageQuantifier *=0.5;
                    break;
                case 5:
                    if(containsLanguage) languageQuantifier*= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }


        double movementsQuantifier = 1;
        for(Map.Entry<String, Integer> movement : template.getViews().getMovements().entrySet()){
            boolean containsMovement = false;
            if(thatPerson.getViews().getMovements().contains(movement.getKey())) containsMovement = true;
            switch (movement.getValue()){
                case -5:
                    if(containsMovement) return Double.NEGATIVE_INFINITY;
                case -4:
                    if(containsMovement) movementsQuantifier *= 0.5;
                    break;
                case -3:
                    if(containsMovement) movementsQuantifier *= 0.75;
                    break;
                case -2:
                    if(containsMovement) movementsQuantifier *= 0.9;
                    break;
                case -1:
                    if(containsMovement) result-=2;
                    break;
                case 0:
                    break;
                case 1:
                    if(containsMovement) result+=2;
                    break;
                case 2:
                    if(containsMovement) movementsQuantifier *= 1.1;
                    break;
                case 3:
                    if(containsMovement) movementsQuantifier *= 1.5;
                    else subculturesQuantifier *=0.75;
                case 4:
                    if(containsMovement) movementsQuantifier *= 2;
                    else subculturesQuantifier *=0.5;
                    break;
                case 5:
                    if(containsMovement) movementsQuantifier *= 2;
                    else return Double.NEGATIVE_INFINITY;
            }
        }
        
        return result
                *genderQuantifier
                *geoQuantifier
                *movementsQuantifier
                *religionQuantifier
                *languageQuantifier
                *subculturesQuantifier
                *personalityTypeQuantifier
                *realPhotoQuantifier;
    }
}
