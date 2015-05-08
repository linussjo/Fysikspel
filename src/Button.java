
public class Button extends Rectangle {

	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setColliderNumber(Collision.BUTTON);
		this.addCollideNumbers(Collision.PLAYER);
		this.addCollideNumbers(Collision.ARROW);
	}

}
