<?php
// Step 1: Get the data from the API
$ch = curl_init("https://coderbyte.com/api/challenges/json/age-counting");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
curl_close($ch);

// Step 2: Decode JSON
$data = json_decode($response, true);

// Step 3: Get the string and split it
$dataString = $data["data"];
$pairs = explode(",", $dataString);

// Step 4: Count age=32 entries (or any other target)
$count = 0;
foreach ($pairs as $pair) {
    $pair = trim($pair);
    if (strpos($pair, "age=") === 0 || strpos($pair, " age=") !== false) {
        $ageValue = (int)explode("=", $pair)[1];
        if ($ageValue === 32) { // Change this number to required age
            $count++;
        }
    }
}

// Step 5: Output the final count
echo $count;
?>
