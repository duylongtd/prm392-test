import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> findThreeSum(int[] nums) {
        // Tạo một danh sách để lưu kết quả
        List<List<Integer>> result = new ArrayList<>();
        
        // Sắp xếp mảng để dễ dàng xử lý các phần tử trùng lặp
        Arrays.sort(nums);

        // Lặp qua mảng, dừng lại ở vị trí thứ ba từ cuối
        for (int i = 0; i < nums.length - 2; i++) {
            // Bỏ qua các phần tử trùng lặp ở vị trí đầu tiên để tránh kết quả trùng lặp
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Nếu tổng bằng 0, thêm bộ ba vào kết quả
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Di chuyển các con trỏ để tìm bộ ba tiếp theo
                    left++;
                    right--;

                    // Bỏ qua các phần tử trùng lặp
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    // Nếu tổng nhỏ hơn 0, tăng con trỏ left để tổng lớn hơn
                    left++;
                } else {
                    // Nếu tổng lớn hơn 0, giảm con trỏ right để tổng nhỏ hơn
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> threeSumResult = solution.findThreeSum(nums);
        System.out.println("Các bộ ba có tổng bằng 0 là: " + threeSumResult);
    }
}
