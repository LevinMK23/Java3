import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore tunnelVolume;
    public Tunnel(int volume) {
        this.tunnelVolume = new Semaphore(volume);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelVolume.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelVolume.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
