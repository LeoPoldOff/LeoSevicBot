class PersonInfo {
    int sex, height, weight, region,
            smokingRange, alcoholRange, sportRange;
    String birthDate;

    PersonInfo(){
        sex = 0;
        birthDate = "";
        height = 0;
        weight = 0;
        region = 0;
        smokingRange = 0;
        alcoholRange = 0;
        sportRange = 0;
    }

    String showInfo() {
        var info = new StringBuilder();
        info.append("Sex: ").append(QuizNode.sexOptions.get(sex));
        info.append("\nDate of birth: ").append(birthDate);
        info.append("\nRegion: ").append(QuizNode.regionOptions.get(region));
        info.append("\nWeight: ").append(weight).append(" kg");
        info.append("\nHeight: ").append(height).append(" sm");
        info.append("\nAttitude to smoking: ").append(QuizNode.smokingOptions.get(smokingRange));
        info.append("\nAttitude to alcohol: ").append(QuizNode.alcoholOptions.get(alcoholRange));
        info.append("\nAttitude to sport: ").append(QuizNode.sportOptions.get(sportRange));
        return info.toString();
    }

    void updateSex(String sex){
        this.sex = Integer.parseInt(sex);
    }
    void updateRegion(String region){
        this.region = Integer.parseInt(region);
    }
    void updateSmokingRange(String smokingRange){
        this.smokingRange = Integer.parseInt(smokingRange);
    }
    void updateAlcoholRange(String AlcoholRange){
        this.alcoholRange = Integer.parseInt(AlcoholRange);
    }
    void updateSportRange(String SportRange){
        this.sportRange = Integer.parseInt(SportRange);
    }
    void updateHeightRange(String height){
        this.height = Integer.parseInt(height);
    }
    void updateWeightRange(String height){
        this.weight = Integer.parseInt(height);
    }
    void updateBirthday(String birthday){
        this.birthDate = birthday;
    }
}
