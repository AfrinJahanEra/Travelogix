class EraserThread implements Runnable {
    private volatile boolean stop;
    private String prompt;

    public EraserThread(String prompt) {
        this.prompt = prompt;
    }

    public void run() {
        try {
            // Print the prompt
            System.out.print(prompt);
            while (!stop) {
                // Print a backspace character to mask the input
                System.out.print("\010*");
                try {
                    // Delay so the masking characters appear gradually
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMasking() {
        this.stop = true;
    }
}
