const PLAYER1 = "player1"
const PLAYER2 = "player2"
const ENEMIE1 = "enemie1"
const DEBUG = false
let gameScene = new Phaser.Scene('Game');
let startScene = new Phaser.Scene('Start');
let endScene = new Phaser.Scene('End');



class Player extends Phaser.Physics.Arcade.Sprite {
    constructor(scene, x, y, id) {
        super(scene, x, y, id);
        this.id = id
        this.scene.add.existing(this);
        this.scene.physics.add.existing(this);
    }
    addAnim(key, frames, frameRate = 20, repeat = -1) {
        for (var f in frames) {
            frames[f] = {
                key: this.id,
                frame: frames[f]
            }
        }
        this.scene.anims.create({
            key: key,
            frames: frames,
            frameRate: frameRate,
            repeat: repeat
        })
    }
    update(cursors) {

    }

}
class Enemie  extends Player {
    constructor(scene, x, y, id) {
        super(scene, x, y, id);
        this.setVelocityX(-230);
    }
    update(cursors) {
        if(this.x<0){
            this.setVelocityX(0)
            this.disableBody(true, true);
        }   
    }
}
class Ship extends Player {
    constructor(scene, x, y, id) {
        super(scene, x, y, id);
    }
    update(cursors) {
        if (cursors.left.isDown) {
            this.setVelocityX(-360);
        } else if (cursors.right.isDown) {
            this.setVelocityX(360);
        } else if (cursors.down.isDown) {
            this.setVelocityY(230);
        } else if (cursors.up.isDown) {
            this.setVelocityY(-230);
        } else {
            this.setVelocityX(0);
            this.setVelocityY(0);
        }
        if(this.x<0){
            this.setVelocityX(0)
            this.setX(2)
        }
    }
}

class Alien extends Player {
    constructor(scene, x, y, id) {
        super(scene, x, y, id);
        this.setGravityY(300)
        this.setBounce(0.6);
        this.setVelocityX(190);
        this.live = true
    }
    update(cursors) {
        if(this.x>5100){
            this.setX(5101)
            this.setVelocityX(0)
            this.anims.play('stop', true);
            this.scene.sound.stopAll();
            this.scene.scene.start('End');
        }else{
            if(this.live){
                this.setVelocityX(160)
            }else{
                this.setVelocityX(0)
                this.setVelocityY(-160)    
            }
            if(this.y<40){
                this.setY(40)
                if(this.live == false){
                    this.disableBody(true, true);
                    this.scene.sound.stopAll();
                    this.scene.scene.start('End');
                }
            }
            if(this.x>5200){
                this.setX(5199)
                this.setVelocityX(0)
            }
        }
    }
    die(){
        this.live =false
        this.anims.play('dead', true);
        this.scene.sound.stopAll();
        this.scene.oopsSound.play()
    }
    hitTile(tile){
        if(this.live==false){
            this.disableBody(true, true);
            this.scene.sound.stopAll();
            this.scene.scene.start('End');
            
        }
        if (tile.properties.death){
            this.die()
        };
        if(this.body.velocity.y<-280){
            this.die()
        }

    }
}

var config = {
    type: Phaser.AUTO,
    width: 920,
    height: 640,
    backgroundColor: '#5E35B1',
    physics: {
        default: 'arcade',
        arcade: {
            debug: DEBUG
        }
    },
    scene: [startScene,gameScene,endScene],
    scale: {
        zoom: 1,
        autoCenter: Phaser.Scale.CENTER_BOTH
    },
    parent: 'game-id'
};
startScene.preload = function () {
    this.load.image('ufo', 'assets/icon.png');
}
startScene.create = function(){
    this.add.tileSprite(100, 80,123,130, "ufo");
    this.add.text(230, 20, 'Welcome to Roswell runner ', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(180, 60, 'One of the little bods from area 51', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(220, 100, 'Is trying to escape to route 66', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(90, 140, 'Our plucky alien will need some bus fare though !', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(60, 180, 'Help your new chum avoid dangers and collect coins', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(110, 230, 'Give your alien a boost with your spacecraft ', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(190, 270, 'Move your craft with arrow keys', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(150, 320, "But don't let your alien chum fall too far !", { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(260, 380, 'Click space key to start', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.spaceKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
    this.spaceKey.on('down', function (key, event) {
        startScene.scene.start('Game');
    });
    
}

endScene.create = function(){
    this.add.text(370, 120, 'Game over!', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.add.text(280, 220, 'Click space key to restart', { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.spaceKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
    this.spaceKey.on('down', function (key, event) {
        endScene.scene.start('Game');
    });
}




gameScene.preload = function () {
    this.load.tilemapTiledJSON('tilemap', 'assets/platform1.json');
    this.load.image('tiles', 'assets/tilesheet_complete.png');
    this.load.image('coin', 'assets/coinGold.png')
    this.load.spritesheet(PLAYER1, 'assets/p3_walk.png', {
        frameWidth: 66,
        frameHeight: 93
    });
    this.load.spritesheet(PLAYER2, 'assets/hover.png', {
        frameWidth: 123,
        frameHeight: 72
    });
    this.load.spritesheet(ENEMIE1, 'assets/fly_fly.png', {
        frameWidth: 66,
        frameHeight: 43
    });
    this.load.image('sky', 'assets/sky.png');
    this.load.image('rocks', 'assets/Rocks.png');
    this.load.image('ground', 'assets/Layer1.png');
    this.load.image('hills', 'assets/Vrstva 8.png');
    this.load.audio('bgmusic', ['assets/Arcade-game-music-loop.mp3' ]);
    this.load.audio('bounce', ['assets/forceField_001.ogg' ]);
    this.load.audio('coin', ['assets/impactGlass_medium_003.ogg' ]);
    this.load.audio('oops', ['assets/secret.ogg' ]);


// *true* param enables looping

}

gameScene.create = function () {

    this.bg_1 = this.add.tileSprite(-400, -30, game.config.width + 800, game.config.height, "sky");
    this.bg_1.setOrigin(0, 0);
    this.bg_1.setScrollFactor(0.05)

    this.bg_2 = this.add.tileSprite(0, -10, 5000, game.config.height, "rocks");
    this.bg_2.setOrigin(0, 0);
    this.bg_2.setScrollFactor(0.5);    
    
    this.bg_3 = this.add.tileSprite(200, 150, 5000, game.config.height, "rocks");
    this.bg_3.setOrigin(0, 0);
    this.bg_3.setScrollFactor(0.7);

    this.player1 = new Alien(this, 120, 420, PLAYER1)
    this.player2 = new Ship(this, 420, 420, PLAYER2)
    this.enemie1 = new Enemie(this,4500,140,ENEMIE1)
    

    const map = this.make.tilemap({
        key: 'tilemap'
    })

   
    const tileset = map.addTilesetImage('tilesheet_complete', 'tiles')
    this.colide = map.createLayer('ground', tileset, 0, 0)
    this.colide.setCollisionByProperty({
        colide: true
    })

    const coins = map.getObjectLayer('Object Layer 1')
    const gcoins = this.physics.add.staticGroup()
    for (c in coins.objects) {
        gcoins.create(coins.objects[c].x + 32, coins.objects[c].y, 'coin');
    }

    this.bg_4 = this.add.tileSprite(0, 490, 14000, game.config.height, "ground");
    this.bg_4.setOrigin(0, 0);
    this.bg_4.setScrollFactor(1.5);

    this.cameras.main.startFollow(this.player2, true);
    this.cameras.main.setBounds(0, 0, this.colide.displayWidth, this.colide.displayHeight);


    this.physics.add.collider(this.player1, this.colide,function(player,tile){
        player.hitTile(tile)
    })
    this.physics.add.collider(this.player2, this.colide)
    this.physics.add.overlap(this.player1, this.player2, function (ob1, ob2) {
        if(ob1.live){
            gameScene.bounceSound.play()
            ob1.setVelocityY(-260)
        }
    })
    this.physics.add.overlap(this.player1, gcoins, function collectStar(player, star) {
        gameScene.coinSound.play()
        gameScene.score++
        star.disableBody(true, true);
    })
    this.physics.add.overlap(this.player1, this.enemie1, function (player, enemie) {
        player.die()
    })

    this.player1.addAnim('right', [0,1,2,3,4])
    this.player1.addAnim('stop', [0])
    this.player1.addAnim('dead', [12])
    this.player2.addAnim('hover',[0,1,2],10)
    this.enemie1.addAnim('fly',[0,1],5,-1)

    this.player1.anims.play('right', true);
    this.player2.anims.play('hover', true);
    this.enemie1.anims.play('fly',true)

    //  Input Events
    this.cursors = this.input.keyboard.createCursorKeys();


    if (DEBUG) {
        const debugGraphics = this.add.graphics().setAlpha(0.75);
        this.colide.renderDebug(debugGraphics, {
            tileColor: null,
            collidingTileColor: new Phaser.Display.Color(244, 134, 48,255), 
            faceColor: new Phaser.Display.Color(40, 39, 37, 255) 
        });
    }
    music = this.sound.add('bgmusic')
    music.play();
    this.bounceSound = this.sound.add('bounce')
    this.coinSound = this.sound.add('coin')
    this.oopsSound = this.sound.add('oops')
    this.score =0
    this.text = this.add.text(20, 20, 'Score: '+this.score, { fontFamily: 'Luckiest Guy', fontSize: '30px'});
    this.text.setScrollFactor(0.0);
}

gameScene.update = function () {
    this.player1.update(this.cursors)
    this.player2.update(this.cursors)
    this.enemie1.update(this.cursors)
    this.text.text= 'Score   : '+this.score
}

var game = new Phaser.Game(config);