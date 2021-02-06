#include <iostream>

using namespace std;

struct E {
    int val;
    int index;

    E(int i, int j) {
        val = i;
        index = j;
    }

    E() {}

};


class Merge {
public:
    E *arr;
    string mode;

    Merge(int size) {
        arr = new E[size];
        this->mode.assign(mode);

    }

    void merge(E arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        E L[n1], R[n2];

        // Copy data to temp arrays L[] and R[]
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays back into arr[l..r]

        // Initial index of first subarray
        int i = 0;

        // Initial index of second subarray
        int j = 0;

        // Initial index of merged subarray
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i].val <= R[j].val) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of
        // L[], if there are any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy the remaining elements of
        // R[], if there are any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

// l is for left index and r is
// right index of the sub-array
// of arr to be sorted */
    void mergeSort(E arr[], int l, int r) {
        if (l >= r) {
            return;//returns recursively
        }
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    void printArray(E A[], int size) {
        for (int i = 0; i < size; i++) {
            cout << "(" << A[i].val << ", " << A[i].index << ") ";
        }
        cout << endl;
    }

// Driver code
};


int main() {
    int nums[] = {14, 21, -5, 0, 2, 0, 0, 2, 3, 14};
    E *arr = new E[10];
    for (int i = 0; i < 10; ++i) {
        arr[i] = E(nums[i], i);

    }
    Merge m(10);
    cout << "original state of array:" << endl;
    m.printArray(arr, 10);
    cout << "sorted array:" << endl;
    cout << "format : (value, original_index)" << endl;
    m.mergeSort(arr, 0, 9);
    m.printArray(arr, 10);

    return 0;
}
