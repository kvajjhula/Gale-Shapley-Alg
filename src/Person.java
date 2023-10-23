public class Person {
    int id;
    Person currentPartner;
    int[] preferences;
    int currentPreferenceIndex = 0;
    public Person(int id, int[] preferences) {
        this.id = id;
        this.preferences = preferences;
        this.currentPartner = null;
    }
    public boolean isEngaged() {
        return currentPartner != null;
    }
    public void engageTo(Person partner) {
        this.currentPartner = partner;
        partner.currentPartner = this;
    }
    public void breakEngagement() {
        this.currentPartner.currentPreferenceIndex++;
        this.currentPartner.currentPartner = null;
        this.currentPartner = null;
    }
}