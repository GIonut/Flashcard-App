package ro.ubbcluj.thesis.domain;

public class Evaluation {

    private Long flashcardId;
    private Integer evaluation;

    public Evaluation(Long flashcardId, Integer evaluation) {
        this.flashcardId = flashcardId;
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "flashcardId=" + flashcardId + ", evaluation=" + evaluation + '}';
    }

    public Long getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Long flashcardId) {
        this.flashcardId = flashcardId;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}
