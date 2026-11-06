# Godforged Trident / 神创-三叉戟-神

以原版三叉戟为原型的强化武器。拥有立体化的手持与投掷模型，分为「神 / 魔 / 仙」三个品阶。三个品阶都自带附魔「忠诚」，投掷后自动飞回，并且可以交给女仆作为远程武器。

## 环境要求

| 项目 | 要求 |
| --- | --- |
| Minecraft | 1.20 / 1.20.1 |
| 模组加载器 | Forge（loader 46 及以上） |
| 前置模组 | 车万女仆（`touhou_little_maid`）1.5.3 及以上 |

## 下载

| 文件 | 版本 |
| --- | --- |
| `车万女仆-神创之约god-trident-1.0.0.jar` | 1.0.0 |

把 jar 放进游戏目录下的 `mods` 文件夹即可。

## 品阶

| 品阶 | 近战 / 投掷伤害 | 命中特效 | 外观 |
| --- | --- | --- | --- |
| 神创-三叉戟-神 | 25 | 不再引发爆炸 | — |
| 神创-三叉戟-魔 | 79 | 点燃目标，类似火焰附加 | 暗红色，酒红粒子 |
| 神创-三叉戟-仙 | 100 | 在命中落点召唤闪电 | 白色与金色交织，白金粒子 |

三者的耐久都是 2500，稀有度为史诗（EPIC）。右键蓄力投掷，沿准星飞行。

## 其他物品

- **生命之心**：女仆饰品，佩戴后每秒回复 20 颗心，仅对车万女仆有效。
- **神之说明书**：游戏内说明书，右键打开分页界面，可查看各品阶用法与合成表。

## 合成配方

**神创-三叉戟-神**

```
GGG
NTN
 N
```

`G` 金锭 · `N` 下界合金锭 · `T` 三叉戟

**神创-三叉戟-魔**

```
 M
BTB
```

`M` 岩浆膏 · `B` 烈焰棒 · `T` 神创-三叉戟-神

**神创-三叉戟-仙**

```
 S
ATA
```

`S` 下界之星 · `A` 紫水晶碎片 · `T` 神创-三叉戟-神

**生命之心**

```
 E
EGE
 E
```

`E` 绿宝石 · `G` 金块

## 从源码构建

工程结构：

```
src/main/java/com/godtrident/      源码（SRG 中间名）
src/main/resources/                资源：assets / data / META-INF
tools/compile.bat                  编译
tools/build.py                     打包
```

1. 需要 JDK 17。把 Forge 1.20.1 的 SRG 名 jar 与运行库放在同一目录，并设为环境变量 `FORGE_CP`（以反斜杠结尾），例如 `set FORGE_CP=C:\myforge\libs\`。该目录至少要有 `forge-1.20.1-*-injected.jar`、`universal.jar`，以及 fmlcore / javafmllanguage / eventbus / guava / gson / joml 等。
2. 运行 `tools\compile.bat`，产物为 `build\classes\com\godtrident\*.class`。
3. 运行 `python tools\build.py`，在工程根目录打包出成品 jar。

注意源码中的类名、字段名使用 Forge 的 SRG 中间名（如 `m_7373_`），这是反向工程产物的写法，直接阅读没有问题，但改代码时需对照 SRG 映射。

## 许可

本项目采用 MIT 许可证，详见 [LICENSE](../LICENSE)。

## 作者

acLuna-a

> 注：jar 内 `mods.toml` 的 `authors` 字段为 `MiMo`，因此游戏内模组列表中显示的作者是 MiMo。
