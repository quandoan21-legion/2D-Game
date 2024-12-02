package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyH = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.setX(100);
        this.setY(100);
        this.setSpeed(4);
        setDirection("down");
    }

    public void getPlayerImage() {
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_up_1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_up_2.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_down_1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_down_2.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_left_1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_left_2.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_right_1.png"))));
            setRight2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy_right_2.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
//        walking animation
        if(keyH.downPressed||keyH.upPressed||keyH.leftPressed||keyH.rightPressed) {
            setSpriteCounter(getSpriteCounter() + 1);
            System.out.println(getSpriteCounter());
            if (getSpriteCounter() >= 15) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }

        if (keyH.upPressed) {
            setDirection("up");
            this.setY(getY() - getSpeed());
        } else if (keyH.downPressed) {
            setDirection("down");
            this.setY(getY() + getSpeed());
        } else if (keyH.leftPressed) {
            setDirection("left");
            this.setX(getX() - getSpeed());
        } else if (keyH.rightPressed) {
            setDirection("right");
            this.setX(getX() + getSpeed());
        }

    }

    public void draw(Graphics2D g2d, int tileSize) {
        BufferedImage image = switch (getDirection()) {
            case "up" -> getSpriteNum() == 1 ? getUp1() : getUp2();
            case "down" -> getSpriteNum() == 1 ? getDown1() : getDown2();
            case "left" -> getSpriteNum() == 1 ? getLeft1() : getLeft2();
            case "right" -> getSpriteNum() == 1 ? getRight1() : getRight2();
            default -> getDown1();
        };
        g2d.drawImage(image, this.getX(), this.getY(), tileSize, tileSize, null);
    }
}
