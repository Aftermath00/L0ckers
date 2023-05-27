import { initializeApp } from "firebase/app";
import { getDatabase, ref, get } from "firebase/database";
import CryptoJS from 'crypto-js';

const firebaseConfig = {
  apiKey: "AIzaSyBoHx8w8aags80Cr-BFiQG8qoTDkrMZHf4",
  authDomain: "cryptoproject-6eeed.firebaseapp.com",
  databaseURL: "https://cryptoproject-6eeed-default-rtdb.firebaseio.com",
  projectId: "cryptoproject-6eeed",
  storageBucket: "cryptoproject-6eeed.appspot.com",
  messagingSenderId: "213447359028",
  appId: "1:213447359028:web:f1744024a687f7f38d6861",
  measurementId: "G-NP234HHGNY"
};

initializeApp(firebaseConfig);


function handleOTPSubmission() {
  const form = document.querySelector("#otpForm");

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const otpNumber = document.getElementById("otp").value;

    const db = getDatabase();
    const otpNumberRef = ref(db, `login/${otpNumber}`);

    get(otpNumberRef)
      .then((snapshot) => {
        if (snapshot.exists()) {
          // Redirect to lockers.html with the otpNumber as a query parameter
          window.location.href = `lockers.html?otpNumber=${otpNumber}`;
        } else {
          console.log("The specified otpNumber does not exist.");
        }
      })
      .catch((error) => {
        console.error("Error retrieving data:", error);
      });
  });
}

function handleLockersPage() {
  const urlParams = new URLSearchParams(window.location.search);
  const otpNumber = urlParams.get('otpNumber');

  const db = getDatabase();
  const otpNumberRef = ref(db, `login/${otpNumber}/cipherText`);

  get(otpNumberRef)
    .then((snapshot) => {
      if (snapshot.exists()) {
        const cipherText = snapshot.val();
        document.getElementById("cipherText").value = cipherText;
      } else {
        console.log("The specified otpNumber does not exist.");
      }
    })
    .catch((error) => {
      console.error("Error retrieving data:", error);
    });

    let secretKey; // Declare secretKey variable

  function setKey(myKey) {
    const key = CryptoJS.SHA1(myKey).toString(CryptoJS.enc.Hex).slice(0, 32);
    secretKey = CryptoJS.enc.Hex.parse(key);
  }

  function decrypt(strToDecrypt) {
    try {
      const decrypted = CryptoJS.AES.decrypt(strToDecrypt, secretKey, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7,
      });
      return decrypted.toString(CryptoJS.enc.Utf8);
    } catch (error) {
      console.log('Error while decrypting: ' + error.toString());
    }
    return null;
  }

  const form = document.querySelector("#lockersForm");

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const secretKey = document.getElementById("secretKey").value;
    const cipherText = document.getElementById("cipherText").value;

    setKey(secretKey);
    const decryptedMessage = decrypt(cipherText, secretKey);
    if (decryptedMessage !== null) {
      document.getElementById("decryptedMessage").value = decryptedMessage;
    }
  });
}

// Call the handleLockersPage function when the lockers.html page is loaded
document.addEventListener("DOMContentLoaded", handleLockersPage);


handleOTPSubmission();
handleLockersPage();