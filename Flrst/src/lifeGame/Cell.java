package lifeGame;

public class Cell {
    private int maxLength;
    private int maxWidth;
    private int nowGeneration;
    private int[][] grid; //һ�����ݴ���һ��ϸ��,0��������1�����

    public Cell(int maxLength, int maxWidth) {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        nowGeneration = 0;
        grid = new int[maxLength + 2][maxWidth + 2];//����һ����ά���飬��Ϊ�������ԣ���ϸ������
        for (int i = 0; i <= maxLength + 1; i++) {
            for (int j = 0; j <= maxWidth + 1; j++)
                grid[i][j] = 0;//��ʼ��Ϊ��ϸ��
        }
    }

    
    public int[][] getGrid() {   //��ȡ��Ԫ�����Ŀ���
        return grid;
    }
    
    public void setGrid(int[][] grid) {  //д�ص�Ԫ��ȥ�ı���
        this.grid = grid;
    }

   
    public int getNowGeneration(){   //��ȡ��Ԫ���ﵱǰ�����Ŀ���
        return nowGeneration;
    }
    
    public void setNowGeneration(int nowGeneration){  //д�ص�Ԫ���ﵱǰ����ȥ�ı���
        this.nowGeneration = nowGeneration;
    }
    
    //�����ʼ��ϸ��
    public void randomCell(){
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = Math.random()>0.5?1:0;//��ʾһ��������0.5�ĸ����Ǻ�ɫ���ɫ������ɫ�Ͱ�ɫ����ϸ������ϸ�����ڷ���������������֡�
    }

    //ϸ������
    public void deleteAllCell(){
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = 0;   //i��j��λ�õķ���ͨͨ��Ϊ��ϸ��
    }

    //����
    public void update() {
        int[][] newGrid = new int[maxLength + 2][maxWidth + 2];//��ĳλ��Ϊ���ĵ�3*3�����ڵķ�����Ϊ�·��񣬼���һ����ϸ�����Դ���������������״̬���б任
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                switch (getNeighborCount(i, j)) {
                    case 2:
                        newGrid[i][j] = grid[i][j]; //һ��ϸ����Χ��2��ϸ��Ϊ����ϸ��״̬���ֲ���
                        break;
                    case 3:
                        newGrid[i][j] = 1; // Cell is alive.һ��ϸ����Χ��3��ϸ��Ϊ�������ϸ��Ϊ������ǰ������Ҳ��Ϊ��
                        break;
                    default:
                        newGrid[i][j] = 0; // Cell is dead.�����������
                }
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = newGrid[i][j];
        nowGeneration++;    //��������
    }

    //��ȡϸ�����ھ�����
    int getNeighborCount(int i1, int j1) {
        int count = 0;
        for (int i = i1 - 1; i <= i1 + 1; i++)
            for (int j = j1 - 1; j <= j1 + 1; j++)
                count += grid[i][j]; //����ھӻ����ţ��ھ������+1
        count -= grid[i1][j1]; //ÿ��ϸ�������Լ����ھӣ������ϸ�������ţ��ھ���-1.

        return count;
    }
}
