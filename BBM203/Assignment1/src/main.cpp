#include<iostream>
#include <fstream>
#include <string>

class Matrix {
private:
    int* width = new int;
    int* length = new int;
    int** grid = nullptr;

public:
    // In constructor I took 2 integer variables and assing them to my variables
    // then i create grid with this integers
    Matrix(int _width, int _length) {
        *width = _width;
        *length = _length;

        grid = new int*[_width];

        for (int i = 0; i < _width; i++) {
            grid[i] = (int*) new int[_length];
        }

    }

    //deconstructor deletes everything that this matrix has created (every element of grid, width and length)
    ~Matrix() {
        for (int i = 0; i < *width; i++) {
            delete[] grid[i];
        }
        delete[] grid;
        delete width;
        delete length;

    }

    // for assign elements to our private grid
    void fillMatrix(int row, int column, int number) {
        grid[row][column] = number;
    }

    int getWidth() const {
        return *width;
    }

    int getLength() const {
        return *length;
    }

    int** getGrid() const {
        return grid;
    }

};

// this class is all about reading files and reading command line arguments
class ReadArgument {
public:

    // this is for map matrix it reads matrix's width and lenght as an integer
    std::pair<int, int> readRectangleMatrix(std::string gridSize) {
        for (int i = 0; i < gridSize.size(); i++) {
            if (gridSize[i] == 'x') {
                gridSize[i] = ' ';
                break;
            }
        }

        int row, column;
        sscanf(gridSize.c_str(), "%d %d", &row, &column);
        return std::make_pair(row, column);
    }


    //this is for reading square matrix like key matrix
    int readSquareMatrix(char* gridSize) {
        int n;
        sscanf(gridSize, "%d", &n);
        return n;
    }

    // reading txt file for assigning values to our map matrix
    void readFile(char* mapFile, Matrix& matrix) {
        std::fstream file;
        file.open(mapFile, std::ios::in);

        int row = matrix.getWidth();
        int column = matrix.getLength();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int currentNum;
                file >> currentNum;
                matrix.fillMatrix(i, j, currentNum);
            }
        }
        file.close();
    }
};

// writing to given output file with location and product
// NOTE: output file needs to be empty because it appends it.
void writeFile(std::string& outputFile, std::pair<int, int>& currentLocation, int& product) {
    std::ofstream file;
    file.open(outputFile, std::ios::app);

    file << currentLocation.first << ',' << currentLocation.second << ':' << product << std::endl;
    file.close();
}
// this is for deleting output file
void deleteFile(std::string outputFile) {
    std::ofstream file;
    file.open(outputFile, std::ios::out);
    file.close();
}


// this multiplys keymatrix and grid matrix in given location
int multipyMatrix(Matrix* keyMatrix, Matrix* mapMatrix, std::pair<int, int>& currentLocation) {
    int product = 0;

    int keyRowIndex = 0;
    int keyColumnIndex = 0;

    for (int i = currentLocation.first - keyMatrix->getWidth() / 2; i <= currentLocation.first + keyMatrix->getWidth() / 2; i++) {
        for (int j = currentLocation.second - keyMatrix->getLength() / 2; j <= currentLocation.second + keyMatrix->getLength() / 2; j++) {
            product += keyMatrix->getGrid()[keyRowIndex][keyColumnIndex] * mapMatrix->getGrid()[i][j];
            keyColumnIndex++;
        }
        keyColumnIndex = 0;
        keyRowIndex++;
    }

    return product;
}

//finds given mod if it is minus it adds mod
int findMod(int& product, int mod) {
    int modProduct = product % mod;
    if (modProduct < 0) {
        modProduct += mod;
    }
    return modProduct;
}

// this is for finding direction for to move our key matrix
std::pair<int, int> findIncrease(int& modProduct, Matrix* keyMatrix) {
    if (modProduct == 1) return std::make_pair(-1 * keyMatrix->getWidth(), 0);
    else if (modProduct == 2) return std::make_pair(1 * keyMatrix->getWidth(), 0);
    else if (modProduct == 3) return std::make_pair(0, 1 * keyMatrix->getLength());
    else return std::make_pair(0, -1 * keyMatrix->getLength());
}

//if we are in out of bound this reverse it is direction and adds to our current location
void changeLocation(std::pair<int, int>& increase, std::pair<int, int>& currentLocation, Matrix* mapMatrix) {
    if (currentLocation.first + increase.first < 0 || currentLocation.first + increase.first >= mapMatrix->getWidth()) {
        increase.first *= -1;
    }
    currentLocation.first += increase.first;

    if (currentLocation.second + increase.second < 0 || currentLocation.second + increase.second >= mapMatrix->getLength()) {
        increase.second *= -1;
    }
    currentLocation.second += increase.second;

}

// main recursion function
void findTreasure(Matrix* keyMatrix, Matrix* mapMatrix, std::pair<int, int>& currentLocation, std::string outputFile) {
    int product = multipyMatrix(keyMatrix, mapMatrix, currentLocation);
    int modProduct = findMod(product, 5);

    int printedNum = (product < 0) ? modProduct : product;
    writeFile(outputFile, currentLocation, printedNum);

    // if mod product is equal to a 0 code will stop
    if (modProduct != 0) {
        std::pair<int, int> increase = findIncrease(modProduct, keyMatrix);
        changeLocation(increase, currentLocation, mapMatrix);
        findTreasure(keyMatrix, mapMatrix, currentLocation, outputFile);
    }
}

// make every operation for running a game, it reads, runs and deletes
void gameManager(char** argv) {
    ReadArgument* read = new ReadArgument();

    std::pair<int, int> sizeOfMapMatrix = read->readRectangleMatrix(argv[1]);
    Matrix* mapMatrix = new Matrix(sizeOfMapMatrix.first, sizeOfMapMatrix.second);
    read->readFile(argv[3], *mapMatrix);

    int sizeOfKeyMatrix = read->readSquareMatrix(argv[2]);
    Matrix* keyMatrix = new Matrix(sizeOfKeyMatrix, sizeOfKeyMatrix);
    read->readFile(argv[4], *keyMatrix);

    int start = sizeOfKeyMatrix / 2;
    std::pair<int, int> currentLocation = std::make_pair(start, start);

    deleteFile(argv[5]);
    findTreasure(keyMatrix, mapMatrix, currentLocation, argv[5]);

    // it prevents memory leaks
    delete read;
    delete mapMatrix;
    delete keyMatrix;
}

int main(int argc, char** argv){
    gameManager(argv);
    return 0;
}
