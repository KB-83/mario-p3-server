package model.main_model;

import javax.persistence.*;

@Entity
@Table(name = "funds")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @OneToOne
//    @JoinColumn(name = "client_id") // The foreign key column in funds table
//    private Client client;
    @Column(name = "health_potion")
    private int healthPotion;
    @Column(name = "invisibility_potion")
    private int invisibilityPotion;
    @Column(name = "speed_potion")
    private int speedPotion;
    @Column(name = "hammer")
    private int hammer;
    @Column(name = "sward")
    private int sward;
    @Column(name = "damage_bomb")
    private int damageBomb;
    @Column(name = "speed_bomb")
    private int speedBomb;

    public Fund() {
    }

    public int getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(int healthPotion) {
        this.healthPotion = healthPotion;
    }

    public int getInvisibilityPotion() {
        return invisibilityPotion;
    }

    public void setInvisibilityPotion(int invisibilityPotion) {
        this.invisibilityPotion = invisibilityPotion;
    }

    public int getSpeedPotion() {
        return speedPotion;
    }

    public void setSpeedPotion(int speedPotion) {
        this.speedPotion = speedPotion;
    }

    public int getHammer() {
        return hammer;
    }

    public void setHammer(int hammer) {
        this.hammer = hammer;
    }

    public int getSward() {
        return sward;
    }

    public void setSward(int sward) {
        this.sward = sward;
    }

    public int getDamageBomb() {
        return damageBomb;
    }

    public void setDamageBomb(int damageBomb) {
        this.damageBomb = damageBomb;
    }

    public int getSpeedBomb() {
        return speedBomb;
    }

    public void setSpeedBomb(int speedBomb) {
        this.speedBomb = speedBomb;
    }

}
