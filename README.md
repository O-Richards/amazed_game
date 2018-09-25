https://drive.google.com/drive/folders/1BB1Ipox8ODB4zkwQ_ujMOI3SNm1YvyXO?usp=sharing

1. Single item, enemy, and player attributes on each tile. Can only hold one of each. 
2. Player is killed if enemy exists on tile. Kill method is only called by weapons or tile, tile kills player on enemy add.
3. Remove collision interface
4. Every usable item performs its own use. use(Enum) : boolean. Returns true on successful use
5. Use builder to construct entities
6. Player is not built using builder. It is its own subclass.
