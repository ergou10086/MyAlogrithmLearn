<?php
const N = 305;
$n = 0;
$m = 0;
$e = array_fill(0, N, []);
$w = array_fill(0, N, 0);
$f = array_fill(0, N, array_fill(0, N, 0));
$siz = array_fill(0, N, 0);

function dfs($u) {
    global $e, $w, $f, $siz, $m;
    $f[$u][1] = $w[$u];
    $siz[$u] = 1;
    foreach ($e[$u] as $v) {
        dfs($v);
        $siz[$u] += $siz[$v];
        for ($j = min($m + 1, $siz[$u]); $j > 0; $j--) {
            for ($k = 0; $k <= min($j - 1, $siz[$v]); $k++) {
                $f[$u][$j] = max($f[$u][$j], $f[$u][$j - $k] + $f[$v][$k]);
            }
        }
    }
}

$input = explode(" ", trim(fgets(STDIN)));
$n = (int)$input[0];
$m = (int)$input[1];

for ($i = 1; $i <= $n; $i++) {
    $input = explode(" ", trim(fgets(STDIN)));
    $k = (int)$input[0];
    $w[$i] = (int)$input[1];
    $e[$k][] = $i;
}

dfs(0);
echo $f[0][$m + 1];
?>