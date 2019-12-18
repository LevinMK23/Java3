import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ForTests {

    //протестируйте функцию
    public List<Integer> sort(List<Integer> data) {
        return data.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public int binarySearch(int [] data, int key, int l, int r){
        if (data.length == 0) return -1;

        int mid = (l + r) / 2;

        if(data[mid] == key) return mid;
        // 1. не обрабатывается ситуация отсутствия искомого элемента
        // 2. не обрабатывается ситуация, когда массив имеет нулевой размер
        if (l >= r || mid == l || mid == r) {
            return -1;
        }
        if(data[mid] > key) return binarySearch(data, key, l, mid);
        return binarySearch(data, key, mid, r);
    }

    //придумайте 2 функции и протестируйте их
    public int[] arrAfter4(int[] arr) throws RuntimeException {
        int pos = -1;
        for (int i = arr.length-1; i >= 0; i--) {
            if (arr[i] == 4) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            throw new RuntimeException();
        } else {
            return Arrays.copyOfRange(arr, pos + 1, arr.length);
        }
    }

    public boolean arrHasOneOrFour(int[] arr) {
        boolean hasOne = false;
        boolean hasFour = false;

        if (arr.length == 0) return false;

        for (int i : arr) {
            if (i == 1) hasOne = true;
            if (i == 4) hasFour = true;
            if (i != 1 && i != 4) return false;
        }

        return (hasOne && hasFour);
    }
}
