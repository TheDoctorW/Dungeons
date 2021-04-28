package unsw.dungeon;

public abstract class Enemy extends Entity{
    private Dungeon dungeon;
    private Player player;
    private boolean check;
    /**
     * 
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public Enemy(Dungeon d,int x,int y) {
        super(x, y);
        this.dungeon = d;
        this.player = d.getPlayer();
        this.check = true;
    }
    /**
     * enemy move up
     */
    public void moveUp() {
        if (checkMove("up")) {
            y().set(getY() - 1);
        }
    }
    /**
     * ememy move down
     */
    public void moveDown() {
        if (checkMove("down")) {
            y().set(getY() + 1);
        }
    }
    /**
     * enemy move left
     */
    public void moveLeft() {
        if (checkMove("left")) {
            // System.out.println("Player Move Left!");
            x().set(getX() - 1);
        }
    }
    /**
     * enemy move right
     */
    public void moveRight() {
        if (checkMove("right")) {
            x().set(getX() + 1);
        }
    }
    /**
     * check enemy can move in different position
     * @param position
     * @return boolean true or false
     */
    public boolean checkMove(String position) {
        switch (position) {
            case "up":
                return (check && getY() > 0 && dungeon.checkEnemyBarrier(getX(), getY() - 1) && dungeon.checkIfGameOver());
            case "down":
                return (check && (getY() < dungeon.getHeight() - 1) && dungeon.checkEnemyBarrier(getX(), getY() + 1) && dungeon.checkIfGameOver());
            case "left":
                return (check && getX() > 0 && dungeon.checkEnemyBarrier(getX() - 1, getY()) && dungeon.checkIfGameOver());
            case "right":
                return (check && (getX() < dungeon.getWidth() - 1) && dungeon.checkEnemyBarrier(getX() + 1, getY()) && dungeon.checkIfGameOver());
        }
        return false;
    }
    /**
     * move the enemy
     */
    public void moveEnemy() {
        if (checkPlayerInvincible()) {
            runOffPlayer();
        } else {
            huntPlayer();
        }
    }
    /**
     * check the player status whether is invincible
     * @return
     */
    public boolean checkPlayerInvincible() {
        this.player = dungeon.getPlayer();
        if (player.getStatus().equals(new Invincible())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * move to the player
     */
    public void runOffPlayer() {
        int x = findDistanceX();
        int y = findDistanceY();
        if (checkIfMoveUpDown()) {
            if (y > 0) {
                if (checkMove("down")) {
                    this.moveDown();
                    return;
                }
            } else if (y < 0) {
                if (checkMove("up")) {
                    this.moveUp();
                    return;
                }
            }
        }
        if (x > 0) {
            if (checkMove("right")) {
                this.moveRight();
                return;
            }
        } else if (x < 0) {
            if (checkMove("left")) {
                this.moveLeft();
                return;
            }
        }
    }
    /**
     * hunt the player
     */
    public void huntPlayer() {
        int x = findDistanceX();
        int y = findDistanceY();
        if (checkIfMoveUpDown()) {
            if (y > 0) {
                if (checkMove("up")) {
                    this.moveUp();
                    return;
                }
            } else if (y < 0) {
                if (checkMove("down")) {
                    this.moveDown();
                    return;
                }
            }
        }
        if (x > 0) {
            if (checkMove("left")) {
                this.moveLeft();
                return;
            }
        } else if (x < 0) {
            if (checkMove("right")) {
                this.moveRight();
                return;
            }
        }
    }
    /**
     * find the distance in x-coord
     * @return
     */
    public int findDistanceX() {
        return this.getX() - player.getX();
    }
    /**
     * find the distance in y-coord
     */
    public int findDistanceY() {
        return this.getY() - player.getY();
    }
    /**
     * check move to up or down
     * @return
     */
    public boolean checkIfMoveUpDown() {
        int x = findDistanceX();
        int y = findDistanceY();
        x = Math.abs(x);
        y = Math.abs(y);
        if (x > y) {
            return false;
        }
        return true;
    }

    public abstract String getName();

    public Player getPlayer() {
        return this.player;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}