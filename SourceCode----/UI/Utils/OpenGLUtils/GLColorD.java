package UI.Utils.OpenGLUtils;

/**
 * @author aalnawai
 */
public class GLColorD {
    public float Red = 0;
    public float Green = 0;
    public float Blue = 0;
    public float Alpha = 0;
    public GLColorD() {
    }
    public GLColorD(GLColorD newGLColorD) {
        Red = newGLColorD.Red;
        Green = newGLColorD.Green;
        Blue = newGLColorD.Blue;
        Alpha = newGLColorD.Alpha;
    }
    public GLColorD(float newRed, float newGreen, float newBlue, float newAlpha) {
        Red = newRed;
        Green = newGreen;
        Blue = newBlue;
        Alpha = newAlpha;
    }
    public GLColorD(float newRed, float newGreen, float newBlue) {
        Red = newRed;
        Green = newGreen;
        Blue = newBlue;
    }
}
