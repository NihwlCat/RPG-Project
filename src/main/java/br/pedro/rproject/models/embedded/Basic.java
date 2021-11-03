package br.pedro.rproject.models.embedded;

public class Basic {
    private int life;
    private int maxLife;

    private int magic;
    private int maxMagic;

    private int sanity;
    private int maxSanity;

    private int awakening;
    private int control;

    private int movement;
    private int vigor;
    private int defense;

    public Basic() {
    }

    public Basic(int maxLife, int maxMagic, int maxSanity) {
        this.maxLife = maxLife;
        this.maxMagic = maxMagic;
        this.maxSanity = maxSanity;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        this.maxMagic = maxMagic;
    }

    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    public int getMaxSanity() {
        return maxSanity;
    }

    public void setMaxSanity(int maxSanity) {
        this.maxSanity = maxSanity;
    }

    public int getAwakening() {
        return awakening;
    }

    public void setAwakening(int awakening) {
        this.awakening = awakening;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Basic{" +
                "life=" + life +
                ", maxLife=" + maxLife +
                ", magic=" + magic +
                ", maxMagic=" + maxMagic +
                ", sanity=" + sanity +
                ", maxSanity=" + maxSanity +
                ", awakening=" + awakening +
                ", control=" + control +
                ", movement=" + movement +
                ", vigor=" + vigor +
                ", defense=" + defense +
                '}';
    }
}
