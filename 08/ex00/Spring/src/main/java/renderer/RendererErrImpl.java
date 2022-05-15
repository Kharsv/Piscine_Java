package renderer;
import preproc.PreProcessor;
public class RendererErrImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String textToPrint) {
        textToPrint = preProcessor.process(textToPrint);
        System.err.println(textToPrint);
    }
}