<?php
$ch = curl_init('https://coderbyte.com/api/challenges/json/age-counting');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HEADER, 0);
$data = curl_exec($ch);
curl_close($ch);

$data = json_decode($data, true);
$items = explode(',', $data['data']);

$count = 0;
for ($i = 0; $i < count($items); $i++) {
    $item = trim($items[$i]);
    if (strpos($item, 'age=') !== false) {
        $age = (int)explode('=', $item)[1];
        if ($age >= 50) {
            $count++;
        }
    }
}

echo $count;
?>
