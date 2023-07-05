package Pojo;

import java.util.List;

public class RequestBody {

    private String name;
    private List<String> languages;
    private List<City> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }


    public static class City {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        private String name;
        private String temperature;

    }
}
