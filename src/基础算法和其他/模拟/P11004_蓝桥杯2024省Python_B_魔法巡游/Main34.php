<?php
function checkNumber($num)
{
    if ($num == 0) return true;
    $n = $num;
    while ($n > 0) {
        $digit = $n % 10;
        if ($digit == 0 || $digit == 2 || $digit == 4) {
            return true;
        }
        $n = (int)($n / 10);
    }
    return false;
}

$n = (int)fgets(STDIN);
$si = explode(" ", trim(fgets(STDIN)));
$ti = explode(" ", trim(fgets(STDIN)));

$inp = 1;
$count = 0;

for ($i = 0; $i < $n; $i++) {
    $temp = ($inp % 2 == 1) ? $si[$i] : $ti[$i];
    if (checkNumber($temp)) {
        $count++;
        $inp++;
    }
}

echo $count . PHP_EOL;
?>