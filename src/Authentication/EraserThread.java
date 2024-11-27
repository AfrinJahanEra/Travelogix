package Authentication;

class EraserThread implements Runnable {
    private volatile boolean stop;
    private String prompt;

    public EraserThread(String prompt) {
        this.prompt = prompt;
    }

    public void run() {
        try {
            System.out.print(prompt);
            while (!stop) {
                System.out.print("\010*");
                try {
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
