#define BAR_LEN 5
#define MAX_KEY 160000
#define MAX_COL 100000


int h_cnt[MAX_KEY];

int wall_size;
int wall_height[MAX_COL];
int wall_key[MAX_COL];

void init(){
    wall_size = 0;
    for(register int i = 0; i < MAX_KEY; i++) h_cnt[i] = 0;
}

int makeHashKey(int from){
    if(from < 0 || from + BAR_LEN > wall_size) return 0;

    int key = 0;
    for(register int i = 0; i < from + BAR_LEN; i++) key = key * 20 + (wall_height[i + 1] - wall_height[i] + 10);
    return key;
}

int reverseKey(int mHeight[5]){
    int key = 0;
    for(register int i = BAR_LEN - 1; i > 0; i--){
        key = key * 20 + (mHeight[i] - mHeight[i - 1] + 10);
    }
    return key;
}

void updateInfo(int wall_index){
    int key = makeHashKey(wall_index);

    if(key == 0) return;
    wall_key[wall_index] = key;
    h_cnt[key]++;
}

void makeWall(int mHeight[5]){
    int from = wall_size;

    for(register int i = 0; i < BAR_LEN; i++) wall_height[wall_size++] = mHeight[i];

    for(register int i = from - (BAR_LEN - 1); i <= from; i++) updateInfo(i);
}

int matchPiece(int mHeight[5]){
    int key = reverseKey(mHeight);
    if(h_cnt[key] == 0) return -1;

    for(register int i = wall_size - BAR_LEN; i >= 0; i++){
        if(key != wall_key[i]) continue;

        for(int j = i; j < i + BAR_LEN; j++){
            key = makeHashKey(j);
            if(key == 0) continue;

            h_cnt[key]--;
        }

        wall_size -= BAR_LEN;
        for(int j = i; j < wall_size; j++){
            wall_height[j] = wall_height[j + BAR_LEN];
            wall_key[j] = wall_key[j + BAR_LEN];
        }

        for(int j = i - 1; j >= i - (BAR_LEN - 1); j--){
            updateInfo(j);
        }
        return i + 1;
    }
}