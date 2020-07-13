package Anno;

public class CustomAnnotation {

    @WorkInProgress
    @Task(description = "Implement tax computations",
            targetDate = "Jan 1, 2012",
            estimatedHours = 50,
            additionalNote = "This implementation is critical for the final launch")
    public static float ComputeTax(float amount, float rate) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ComputeTax(100.0f,0.7f));
    }
}

@interface WorkInProgress {
}
@interface Task {
    String description();
    String targetDate();
    int estimatedHours();
    String additionalNote();
}