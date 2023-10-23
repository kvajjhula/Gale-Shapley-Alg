public class Woman extends Person{
    public Woman(int id, int[] preferences) {
        super(id, preferences);
    }
    public boolean prefersNewSuitor(Person suitor) {
        int suitorRanking = suitor.id;
        int currentPartnerRanking = this.currentPartner.id;
        for (int i = 0; i < this.preferences.length; i++) {
            if (preferences[i] == suitorRanking) {
                return true;
            }
            if (preferences[i] == currentPartnerRanking) {
                return false;
            }
        }
        return false;
    }
}