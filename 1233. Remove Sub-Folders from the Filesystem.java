// Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

// If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

// The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

// For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 

// Example 1:

// Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
// Output: ["/a","/c/d","/c/f"]
// Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
// Example 2:

// Input: folder = ["/a","/a/b/c","/a/b/d"]
// Output: ["/a"]
// Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
// Example 3:

// Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
// Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 

// Constraints:

// 1 <= folder.length <= 4 * 104
// 2 <= folder[i].length <= 100
// folder[i] contains only lowercase letters and '/'.
// folder[i] always starts with the character '/'.
// Each folder name is unique.


// Approach 1: Using Set
// Intuition
// The challenge is to efficiently determine when one folder is a sub-folder of another by finding folder paths and identifying hierarchical relationships. We can achieve this by storing all folder paths in a set, allowing us to quickly check if a folder is nested within another.

// Once we have the set, the next logical step is to look at each folder in the list and check its “parent” paths by trimming off one part of the path at a time. For instance, if we have a folder "/a/b/c", we’d first check "/a/b", then "/a". If any of these exist in the set, it means the current folder is a sub-folder, so we can skip it. On the other hand, if no parent path exists in the set, we can conclude it’s an independent folder and add it to our result.

// By breaking each folder down like this, we can establish a relationship between folders and sub-folders. This approach is straightforward to understand if we’re dealing with a small number of folders, but it's not very efficient for large inputs since it involves checking multiple prefixes for each folder.

// Algorithm
// Create a set folderSet containing all folder paths from the folder array for quick look-up.

// Initialize an empty array result to store folders that are not sub-folders.

// For each folder f in folder:

// Set a flag isSubFolder to false.

// Initialize prefix with the value of f to represent the current folder path.

// Use a loop to check each parent path of prefix:

// Find the position of the last / in prefix and remove everything after it to get the parent path.

// If no / is found, break out of the loop (no more parent paths).

// Check if this parent path exists in folderSet:

// If it does, mark isSubFolder as true and exit the loop since f is a sub-folder.
// If isSubFolder is still false after checking all parent paths, add f to result.

// After all, folders have been processed, return result which contains only the top-level folders (non-sub-folders). 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public List<String> removeSubfolders(String[] folder) {
        // Create a set to store all folder paths for fast lookup
        Set<String> folderSet = new HashSet<>(Arrays.asList(folder));
        List<String> result = new ArrayList<>();

        // Iterate through each folder to check if it's a sub-folder
        for (String f : folder) {
            boolean isSubFolder = false;
            String prefix = f;

            // Check all prefixes of the current folder path
            while (!prefix.isEmpty()) {
                int pos = prefix.lastIndexOf('/');
                if (pos == -1) break;

                // Reduce the prefix to its parent folder
                prefix = prefix.substring(0, pos);

                // If the parent folder exists in the set, mark as sub-folder
                if (folderSet.contains(prefix)) {
                    isSubFolder = true;
                    break;
                }
            }

            // If not a sub-folder, add it to the result
            if (!isSubFolder) {
                result.add(f);
            }
        }

        return result;
    }
}


// Approach 2: Using Sorting
// Intuition
// To filter out sub-folders, we can take advantage of the natural order of paths by sorting the list of folders alphabetically. In this order, any sub-folder will appear directly after its parent folder. We can then filter sub-folders in a single pass through the sorted list.

// Starting with an empty result list, we add the first folder. As we continue through the list, each folder is either a sub-folder of the last added folder (if it starts with that path plus a /) or it's an independent folder. For example, if the last added folder was "/a", any folder beginning with "/a/" is a sub-folder and can be skipped. Otherwise, we add the folder to the result list.

// Algorithm
// Sort the folder array alphabetically so that any sub-folder appears immediately after its parent folder.

// Initialize an empty array result to store non-sub-folder paths and add the first folder in folder to result as a baseline.

// For each folder folder[i] starting from the second folder:

// Retrieve the last folder path added to result and append a / to it, storing it as lastFolder.

// Check if folder[i] starts with lastFolder:

// If it does, skip this folder since it is a sub-folder of lastFolder.
// Otherwise, add folder[i] to result because it is not a sub-folder.
// After iterating through all folders, return result, which contains only the top-level folders (non-sub-folders).

class Solution2 {

    public List<String> removeSubfolders(String[] folder) {
        // Sort the folders alphabetically
        Arrays.sort(folder);

        // Initialize the result list and add the first folder
        List<String> result = new ArrayList<>();
        result.add(folder[0]);

        // Iterate through each folder and check if it's a sub-folder of the last added folder in the result
        for (int i = 1; i < folder.length; i++) {
            String lastFolder = result.get(result.size() - 1);
            lastFolder += '/';

            // Check if the current folder starts with the last added folder path
            if (!folder[i].startsWith(lastFolder)) {
                result.add(folder[i]);
            }
        }

        // Return the result containing only non-sub-folders
        return result;
    }
}