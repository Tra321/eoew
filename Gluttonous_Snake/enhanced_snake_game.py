import pygame
import random
import sys
import os

# 初始化pygame
pygame.init()
pygame.mixer.init()  # 初始化音频混合器

# 定义颜色
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
GRAY = (200, 200, 200)

# 设置游戏窗口
WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600
BLOCK_SIZE = 20
FPS_EASY = 10
FPS_MEDIUM = 15
FPS_HARD = 20

# 创建游戏窗口
screen = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('增强版贪吃蛇游戏')
clock = pygame.time.Clock()

# 加载字体
try:
    font_small = pygame.font.SysFont('simhei', 25)
    font_medium = pygame.font.SysFont('simhei', 35)
    font_large = pygame.font.SysFont('simhei', 50)
except:
    font_small = pygame.font.Font(None, 25)
    font_medium = pygame.font.Font(None, 35)
    font_large = pygame.font.Font(None, 50)

# 尝试加载音效
try:
    eat_sound = pygame.mixer.Sound('eat.wav')
    game_over_sound = pygame.mixer.Sound('game_over.wav')
    sound_enabled = True
except:
    # 如果找不到音效文件，禁用音效
    eat_sound = None
    game_over_sound = None
    sound_enabled = False
    print("警告：未找到音效文件，音效已禁用")

# 定义蛇类
class Snake:
    def __init__(self):
        self.length = 3
        self.positions = [(WINDOW_WIDTH // 2, WINDOW_HEIGHT // 2)]
        self.direction = (1, 0)  # 默认向右移动
        self.color = GREEN
        self.score = 0
        self.last_key = pygame.K_RIGHT

    def get_head_position(self):
        return self.positions[0]

    def turn(self, point):
        # 防止蛇向反方向移动
        if self.length > 1 and (point[0] * -1, point[1] * -1) == self.direction:
            return
        else:
            self.direction = point

    def move(self):
        cur = self.get_head_position()
        x, y = self.direction
        new = (((cur[0] + (x * BLOCK_SIZE)) % WINDOW_WIDTH), (cur[1] + (y * BLOCK_SIZE)) % WINDOW_HEIGHT)
        
        # 检查是否撞到自己
        if len(self.positions) > 1 and new in self.positions[2:]:
            return False
        else:
            self.positions.insert(0, new)
            if len(self.positions) > self.length:
                self.positions.pop()
            return True

    def reset(self):
        self.length = 3
        self.positions = [(WINDOW_WIDTH // 2, WINDOW_HEIGHT // 2)]
        self.direction = (1, 0)
        self.score = 0
        self.last_key = pygame.K_RIGHT

    def draw(self, surface):
        # 绘制蛇头（不同颜色）
        head = self.positions[0]
        head_rect = pygame.Rect((head[0], head[1]), (BLOCK_SIZE, BLOCK_SIZE))
        pygame.draw.rect(surface, BLUE, head_rect)
        pygame.draw.rect(surface, BLACK, head_rect, 1)
        
        # 绘制蛇身
        for p in self.positions[1:]:
            rect = pygame.Rect((p[0], p[1]), (BLOCK_SIZE, BLOCK_SIZE))
            pygame.draw.rect(surface, self.color, rect)
            pygame.draw.rect(surface, BLACK, rect, 1)

    def handle_keys(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    pygame.quit()
                    sys.exit()
                elif event.key == pygame.K_UP and self.last_key != pygame.K_DOWN:
                    self.turn((0, -1))
                    self.last_key = pygame.K_UP
                elif event.key == pygame.K_DOWN and self.last_key != pygame.K_UP:
                    self.turn((0, 1))
                    self.last_key = pygame.K_DOWN
                elif event.key == pygame.K_LEFT and self.last_key != pygame.K_RIGHT:
                    self.turn((-1, 0))
                    self.last_key = pygame.K_LEFT
                elif event.key == pygame.K_RIGHT and self.last_key != pygame.K_LEFT:
                    self.turn((1, 0))
                    self.last_key = pygame.K_RIGHT
                return event.key
        return None

# 定义食物类
class Food:
    def __init__(self):
        self.position = (0, 0)
        self.color = RED
        self.randomize_position()

    def randomize_position(self):
        self.position = (random.randint(0, (WINDOW_WIDTH - BLOCK_SIZE) // BLOCK_SIZE) * BLOCK_SIZE,
                         random.randint(0, (WINDOW_HEIGHT - BLOCK_SIZE) // BLOCK_SIZE) * BLOCK_SIZE)

    def draw(self, surface):
        r = pygame.Rect((self.position[0], self.position[1]), (BLOCK_SIZE, BLOCK_SIZE))
        pygame.draw.rect(surface, self.color, r)
        pygame.draw.rect(surface, BLACK, r, 1)

# 定义按钮类
class Button:
    def __init__(self, x, y, width, height, text, color, hover_color):
        self.rect = pygame.Rect(x, y, width, height)
        self.text = text
        self.color = color
        self.hover_color = hover_color
        self.current_color = color
        self.text_surface = font_medium.render(text, True, BLACK)
        self.text_rect = self.text_surface.get_rect(center=self.rect.center)

    def draw(self, surface):
        pygame.draw.rect(surface, self.current_color, self.rect)
        pygame.draw.rect(surface, BLACK, self.rect, 2)
        surface.blit(self.text_surface, self.text_rect)

    def is_hovered(self, pos):
        if self.rect.collidepoint(pos):
            self.current_color = self.hover_color
            return True
        self.current_color = self.color
        return False

# 显示分数
def draw_score(surface, score):
    score_text = font_small.render(f'分数: {score}', True, BLUE)
    surface.blit(score_text, (10, 10))

# 显示游戏结束
def draw_game_over(surface, score):
    game_over_text = font_large.render('游戏结束!', True, RED)
    score_text = font_medium.render(f'最终分数: {score}', True, BLACK)
    restart_text = font_medium.render('按R键重新开始', True, BLACK)
    menu_text = font_medium.render('按M键返回主菜单', True, BLACK)
    
    surface.blit(game_over_text, (WINDOW_WIDTH // 2 - 100, WINDOW_HEIGHT // 2 - 100))
    surface.blit(score_text, (WINDOW_WIDTH // 2 - 80, WINDOW_HEIGHT // 2 - 30))
    surface.blit(restart_text, (WINDOW_WIDTH // 2 - 100, WINDOW_HEIGHT // 2 + 20))
    surface.blit(menu_text, (WINDOW_WIDTH // 2 - 100, WINDOW_HEIGHT // 2 + 70))

# 显示暂停界面
def draw_pause(surface):
    s = pygame.Surface((WINDOW_WIDTH, WINDOW_HEIGHT), pygame.SRCALPHA)
    s.fill((0, 0, 0, 128))  # 半透明黑色背景
    surface.blit(s, (0, 0))
    
    pause_text = font_large.render('游戏暂停', True, WHITE)
    continue_text = font_medium.render('按P键继续游戏', True, WHITE)
    
    surface.blit(pause_text, (WINDOW_WIDTH // 2 - 100, WINDOW_HEIGHT // 2 - 50))
    surface.blit(continue_text, (WINDOW_WIDTH // 2 - 100, WINDOW_HEIGHT // 2 + 20))

# 显示开始界面
def show_start_screen():
    # 创建按钮
    start_button = Button(WINDOW_WIDTH // 2 - 100, 200, 200, 50, "开始游戏", GREEN, YELLOW)
    easy_button = Button(WINDOW_WIDTH // 2 - 100, 300, 200, 50, "简单难度", BLUE, YELLOW)
    medium_button = Button(WINDOW_WIDTH // 2 - 100, 370, 200, 50, "中等难度", BLUE, YELLOW)
    hard_button = Button(WINDOW_WIDTH // 2 - 100, 440, 200, 50, "困难难度", BLUE, YELLOW)
    quit_button = Button(WINDOW_WIDTH // 2 - 100, 510, 200, 50, "退出游戏", RED, YELLOW)
    
    # 默认难度
    difficulty = "medium"
    
    # 更新按钮颜色以显示当前选择的难度
    easy_button.current_color = GRAY
    medium_button.current_color = BLUE
    hard_button.current_color = GRAY
    
    while True:
        screen.fill(WHITE)
        
        # 显示标题
        title_text = font_large.render("贪吃蛇游戏", True, GREEN)
        screen.blit(title_text, (WINDOW_WIDTH // 2 - 100, 100))
        
        # 获取鼠标位置
        mouse_pos = pygame.mouse.get_pos()
        
        # 检查按钮悬停和点击
        start_button.is_hovered(mouse_pos)
        easy_hovered = easy_button.is_hovered(mouse_pos)
        medium_hovered = medium_button.is_hovered(mouse_pos)
        hard_hovered = hard_button.is_hovered(mouse_pos)
        quit_button.is_hovered(mouse_pos)
        
        # 绘制按钮
        start_button.draw(screen)
        easy_button.draw(screen)
        medium_button.draw(screen)
        hard_button.draw(screen)
        quit_button.draw(screen)
        
        # 处理事件
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.MOUSEBUTTONDOWN:
                if start_button.rect.collidepoint(mouse_pos):
                    return difficulty
                elif easy_button.rect.collidepoint(mouse_pos):
                    difficulty = "easy"
                    easy_button.current_color = BLUE
                    medium_button.current_color = GRAY
                    hard_button.current_color = GRAY
                elif medium_button.rect.collidepoint(mouse_pos):
                    difficulty = "medium"
                    easy_button.current_color = GRAY
                    medium_button.current_color = BLUE
                    hard_button.current_color = GRAY
                elif hard_button.rect.collidepoint(mouse_pos):
                    difficulty = "hard"
                    easy_button.current_color = GRAY
                    medium_button.current_color = GRAY
                    hard_button.current_color = BLUE
                elif quit_button.rect.collidepoint(mouse_pos):
                    pygame.quit()
                    sys.exit()
        
        pygame.display.update()
        clock.tick(15)

# 主游戏函数
def main():
    while True:
        # 显示开始界面并获取难度
        difficulty = show_start_screen()
        
        # 根据难度设置FPS
        if difficulty == "easy":
            fps = FPS_EASY
        elif difficulty == "medium":
            fps = FPS_MEDIUM
        else:
            fps = FPS_HARD
        
        # 初始化游戏对象
        snake = Snake()
        food = Food()
        game_over = False
        paused = False
        
        # 游戏主循环
        while True:
            key_pressed = None
            
            # 处理事件
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        pygame.quit()
                        sys.exit()
                    elif event.key == pygame.K_p and not game_over:
                        paused = not paused
                    elif event.key == pygame.K_r and game_over:
                        # 重新开始游戏
                        snake.reset()
                        food.randomize_position()
                        game_over = False
                    elif event.key == pygame.K_m and game_over:
                        # 返回主菜单
                        break
                    else:
                        key_pressed = event.key
            
            # 如果按下M键返回主菜单
            if key_pressed == pygame.K_m and game_over:
                break
            
            # 如果游戏暂停，只绘制暂停界面
            if paused:
                draw_pause(screen)
                pygame.display.update()
                clock.tick(fps)
                continue
            
            if not game_over:
                # 处理按键输入
                if key_pressed == pygame.K_UP and snake.last_key != pygame.K_DOWN:
                    snake.turn((0, -1))
                    snake.last_key = pygame.K_UP
                elif key_pressed == pygame.K_DOWN and snake.last_key != pygame.K_UP:
                    snake.turn((0, 1))
                    snake.last_key = pygame.K_DOWN
                elif key_pressed == pygame.K_LEFT and snake.last_key != pygame.K_RIGHT:
                    snake.turn((-1, 0))
                    snake.last_key = pygame.K_LEFT
                elif key_pressed == pygame.K_RIGHT and snake.last_key != pygame.K_LEFT:
                    snake.turn((1, 0))
                    snake.last_key = pygame.K_RIGHT
                
                # 移动蛇
                if not snake.move():
                    game_over = True
                    if sound_enabled:
                        game_over_sound.play()
                
                # 检查是否吃到食物
                if snake.get_head_position() == food.position:
                    snake.length += 1
                    snake.score += 10
                    food.randomize_position()
                    if sound_enabled:
                        eat_sound.play()
            
            # 绘制游戏界面
            screen.fill(WHITE)
            
            # 绘制网格背景
            for x in range(0, WINDOW_WIDTH, BLOCK_SIZE):
                for y in range(0, WINDOW_HEIGHT, BLOCK_SIZE):
                    rect = pygame.Rect(x, y, BLOCK_SIZE, BLOCK_SIZE)
                    pygame.draw.rect(screen, GRAY, rect, 1)
            
            # 绘制蛇和食物
            snake.draw(screen)
            food.draw(screen)
            
            # 显示分数
            draw_score(screen, snake.score)
            
            # 显示当前难度
            difficulty_text = font_small.render(f'难度: {"简单" if difficulty == "easy" else "中等" if difficulty == "medium" else "困难"}', True, BLUE)
            screen.blit(difficulty_text, (WINDOW_WIDTH - 150, 10))
            
            # 显示控制提示
            if not game_over:
                pause_hint = font_small.render('按P键暂停', True, BLACK)
                screen.blit(pause_hint, (WINDOW_WIDTH // 2 - 50, 10))
            
            # 如果游戏结束，显示游戏结束界面
            if game_over:
                draw_game_over(screen, snake.score)
            
            pygame.display.update()
            clock.tick(fps)

if __name__ == "__main__":
    main()
