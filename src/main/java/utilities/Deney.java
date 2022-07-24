package utilities;

public class Deney {

    public static void main(String[] args) {

        int arr []={7,3,9,5,25};
        int max = arr[0];

        for (int i =0; i<arr.length;i++){
            if (max<arr[i]){
                max=arr[i];
            }
        }
        System.out.println(max);
    }
}
