package wx;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPObjectFactory;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;
// --- <<IS-END-IMPORTS>> ---

public final class pgp

{
	// ---( internal utility methods )---

	final static pgp _instance = new pgp();

	static pgp _newInstance() { return new pgp(); }

	static pgp _cast(Object o) { return (pgp)o; }

	// ---( server methods )---




	public static final void decrypt (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decrypt)>> ---
		// @sigtype java 3.5
		// [i] field:0:required encryptedvalue
		// [i] field:0:required privatekey
		// [i] field:0:required password
		// [o] field:0:required decryptedvalue
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	encryptedvalue = IDataUtil.getString( pipelineCursor, "encryptedvalue" );
			String	privatekey = IDataUtil.getString( pipelineCursor, "privatekey" );
			String	password = IDataUtil.getString( pipelineCursor, "password" );
		pipelineCursor.destroy();
		String decryptedvalue=null;
		ByteArrayInputStream	secKey = new ByteArrayInputStream(privatekey.getBytes());  
		  try {
			char JavaCharArray[]=password.toCharArray();
			byte[] byteArr1= encryptedvalue.getBytes();
			byte[] decrypted = decrypt(byteArr1, secKey, JavaCharArray);
			decryptedvalue = new String(decrypted);
		} catch (NoSuchProviderException | IOException | PGPException e) {
		e.printStackTrace();
		}	
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "decryptedvalue", decryptedvalue );
		pipelineCursor_1.destroy();	
		// --- <<IS-END>> ---

                
	}



	public static final void encrypt (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encrypt)>> ---
		// @sigtype java 3.5
		// [i] field:0:required publickey
		// [i] field:0:required valueyoencrypt
		// [o] field:0:required encryptedvalue
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	publickey = IDataUtil.getString( pipelineCursor, "publickey" );
			String	valueyoencrypt = IDataUtil.getString( pipelineCursor, "valueyoencrypt" );
		pipelineCursor.destroy();
		 String encryptedvalue=null;
		 ByteArrayInputStream in=new ByteArrayInputStream(publickey.getBytes());
		 try {
			PGPPublicKey publicKey = readPublicKey(in);
			byte[] byteArr = valueyoencrypt.getBytes();
			byte[] byteArr1 = encrypt( byteArr,  publicKey, true );
			 String replaceString = new String(byteArr1);	
			 encryptedvalue=replaceString.replace("Version: BCPG v1.65","");
		} catch (Exception e1  ) {
			e1.printStackTrace();
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "encryptedvalue", encryptedvalue );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void sign (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sign)>> ---
		// @sigtype java 3.5
		// [i] field:0:required privatekey
		// [i] field:0:required inputmessage
		// [i] field:0:required password
		// [o] object:0:required signedmessage
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	privatekey = IDataUtil.getString( pipelineCursor, "privatekey" );
			String	inputmessage = IDataUtil.getString( pipelineCursor, "inputmessage" );
			String	password = IDataUtil.getString( pipelineCursor, "password" );
		pipelineCursor.destroy();
		InputStream result = new ByteArrayInputStream(privatekey.getBytes(StandardCharsets.UTF_8));
		String signedmessage=null;
		byte[] signed_message =null;
		 ObjectInputStream is =null;
		try {			
			PGPSecretKey key = readSecretKey(result);
			PGPPrivateKey k = extractPrivateKey(key, password.toCharArray());
			byte[] byteArr = inputmessage.getBytes();
		    signed_message = sign(byteArr, key, password, false);
		    ByteArrayInputStream in = new ByteArrayInputStream(signed_message);
		    is = new ObjectInputStream(in);
		    //signedmessage =  new String(signed_message);
		} catch (IOException | PGPException e) {
		e.printStackTrace();
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "signedmessage", signed_message );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final BouncyCastleProvider provider = new BouncyCastleProvider();
	static {
		Security.addProvider(provider);
	}
	public static boolean verify(byte[] signedMessage, PGPPublicKey publicKey) throws PGPException {
		try {
			InputStream in = PGPUtil.getDecoderStream(new ByteArrayInputStream(signedMessage));
			JcaPGPObjectFactory pgpFact = new JcaPGPObjectFactory(in);
			PGPCompressedData c1 = (PGPCompressedData) pgpFact.nextObject();
			pgpFact = new JcaPGPObjectFactory(c1.getDataStream());
			PGPOnePassSignatureList p1 = (PGPOnePassSignatureList) pgpFact.nextObject();
			PGPOnePassSignature ops = p1.get(0);
			PGPLiteralData p2 = (PGPLiteralData) pgpFact.nextObject();
			InputStream dIn = p2.getInputStream();
			int ch;
			ops.init(new JcaPGPContentVerifierBuilderProvider().setProvider(provider), publicKey);
			while ((ch = dIn.read()) >= 0) {
				ops.update((byte) ch);
			}
			PGPSignatureList p3 = (PGPSignatureList) pgpFact.nextObject();
			if (ops.verify(p3.get(0))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new PGPException("Error in verify", e);
		}
	}
	public static byte[] sign( byte[] message, PGPSecretKey secretKey, String secretPwd, boolean armor )throws PGPException
	{
	    try
	    {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        OutputStream theOut = armor ? new ArmoredOutputStream( out ) : out;
	        PGPPrivateKey pgpPrivKey = secretKey.extractPrivateKey(
	        new JcePBESecretKeyDecryptorBuilder().setProvider( provider ).build( secretPwd.toCharArray() ) );
	        PGPSignatureGenerator sGen = new PGPSignatureGenerator(
	        new JcaPGPContentSignerBuilder( secretKey.getPublicKey().getAlgorithm(), PGPUtil.SHA1 )
	                    .setProvider( provider ) );
	        sGen.init( PGPSignature.BINARY_DOCUMENT, pgpPrivKey );
	        Iterator it = secretKey.getPublicKey().getUserIDs();
	        if ( it.hasNext() )
	        {
	            PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
	            spGen.setSignerUserID( false, ( String ) it.next() );
	            sGen.setHashedSubpackets( spGen.generate() );
	        }
	        PGPCompressedDataGenerator cGen = new PGPCompressedDataGenerator( PGPCompressedData.ZLIB );
	        BCPGOutputStream bOut = new BCPGOutputStream( cGen.open( theOut ) );
	        sGen.generateOnePassVersion( false ).encode( bOut );
	        PGPLiteralDataGenerator lGen = new PGPLiteralDataGenerator();
	        OutputStream lOut =lGen.open( bOut, PGPLiteralData.BINARY, "filename", new Date(), new byte[4096] );     
	        InputStream fIn = new ByteArrayInputStream( message );
	        int ch;
	        while ( ( ch = fIn.read() ) >= 0 )
	        {
	            lOut.write( ch );
	            sGen.update( ( byte ) ch );
	        }
	        lGen.close();
	        sGen.generate().encode( bOut );
	        cGen.close();
	        theOut.close();
	        return out.toByteArray();
	    }
	    catch ( Exception e )
	    {
	        throw new PGPException( "Error in sign", e );
	    }
	}
	static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
		PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(input),
				new JcaKeyFingerprintCalculator());
		Iterator keyRingIter = pgpSec.getKeyRings();
		while (keyRingIter.hasNext()) {
			PGPSecretKeyRing keyRing = (PGPSecretKeyRing) keyRingIter.next();
			Iterator keyIter = keyRing.getSecretKeys();
			while (keyIter.hasNext()) {
				PGPSecretKey key = (PGPSecretKey) keyIter.next();
				if (key.isSigningKey()) {
					return key;
				}
			}
		}
		throw new IllegalArgumentException("Can't find signing key in key ring.");
	  }
	public static byte[] encrypt(final byte[] message, final PGPPublicKey publicKey, boolean armored)
			throws PGPException {
		try {
			final ByteArrayInputStream in = new ByteArrayInputStream(message);
			final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			final PGPLiteralDataGenerator literal = new PGPLiteralDataGenerator();
			final PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(CompressionAlgorithmTags.ZIP);
			final OutputStream pOut = literal.open(comData.open(bOut), PGPLiteralData.BINARY, "filename",
					in.available(), new Date());
			Streams.pipeAll(in, pOut);
			comData.close();
			final byte[] bytes = bOut.toByteArray();
			final PGPEncryptedDataGenerator generator = new PGPEncryptedDataGenerator(
					new JcePGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.AES_256).setWithIntegrityPacket(true)
							.setSecureRandom(new SecureRandom())
							.setProvider(provider));
			generator.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(publicKey).setProvider(provider));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputStream theOut = armored ? new ArmoredOutputStream(out) : out;
			OutputStream cOut = generator.open(theOut, bytes.length);
			cOut.write(bytes);
			cOut.close();
			theOut.close();
			return out.toByteArray();
		} catch (Exception e) {
			throw new PGPException("Error in encrypt", e);
		}
	}
	@SuppressWarnings("resource")
	public static PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
		Security.addProvider(new BouncyCastleProvider());
		in = PGPUtil.getDecoderStream(in);
		PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in, new JcaKeyFingerprintCalculator());
		Iterator rIt = pgpPub.getKeyRings();
		while (rIt.hasNext()) {
			PGPPublicKeyRing kRing = (PGPPublicKeyRing) rIt.next();
			Iterator kIt = kRing.getPublicKeys();
			while (kIt.hasNext()) {
				PGPPublicKey k = (PGPPublicKey) kIt.next();
				if (k.isEncryptionKey()) {
					return k;
				}
			}
		}
		throw new IllegalArgumentException("Can't find encryption key in key ring.");
	}
	protected static byte[] decrypt(byte[] encrypted, InputStream keyIn, char[] password)
			throws IOException, PGPException, NoSuchProviderException {
	
		InputStream decodeIn = PGPUtil.getDecoderStream(new ByteArrayInputStream(encrypted));
		BcPGPObjectFactory pgpF = new BcPGPObjectFactory(decodeIn);
		decodeIn.close();
		PGPEncryptedDataList enc = null;
		Object o = pgpF.nextObject();
		if (o instanceof PGPEncryptedDataList) {
			enc = (PGPEncryptedDataList) o;
		} else {
			enc = (PGPEncryptedDataList) pgpF.nextObject();
		}
		PGPPrivateKey sKey = null;
		PGPPublicKeyEncryptedData pbe = null;
		PGPSecretKeyRingCollection pgpSec = new BcPGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
		for (int i = 0; i < enc.size() && sKey == null; i++) {
			Object encryptedData = enc.get(i);
			pbe = (PGPPublicKeyEncryptedData) encryptedData;
			sKey = findSecretKey(pgpSec, pbe.getKeyID(), password);
		}
		if (sKey == null) {
			throw new IllegalArgumentException("secret key for message not found.");
		}
		BcPublicKeyDataDecryptorFactory pkdf = new BcPublicKeyDataDecryptorFactory(sKey);
		InputStream clear = pbe.getDataStream(pkdf);
		PGPObjectFactory pgpFact = new BcPGPObjectFactory(clear);
		PGPCompressedData cData = (PGPCompressedData) pgpFact.nextObject();
		pgpFact = new BcPGPObjectFactory(cData.getDataStream());
		PGPLiteralData ld = (PGPLiteralData) pgpFact.nextObject();
		InputStream unc = ld.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = unc.read()) >= 0) {
			out.write(ch);
		}
		byte[] returnBytes = out.toByteArray();
		clear.close();
		out.close();
		unc.close();
		return returnBytes;
	}
	protected static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass)
			throws PGPException, NoSuchProviderException {
		PGPPrivateKey privateKey = null;
		PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
		if (pgpSecKey == null) {
			return null;
		}
		privateKey = extractPrivateKey(pgpSecKey, pass);
		return privateKey;
	}
	private static PGPPrivateKey extractPrivateKey(PGPSecretKey pgpSecKey, char[] passPhrase) throws PGPException {
		PGPPrivateKey privateKey = null;
		BcPGPDigestCalculatorProvider calculatorProvider = new BcPGPDigestCalculatorProvider();
		BcPBESecretKeyDecryptorBuilder secretKeyDecryptorBuilder = new BcPBESecretKeyDecryptorBuilder(
				calculatorProvider);
		PBESecretKeyDecryptor pBESecretKeyDecryptor = secretKeyDecryptorBuilder.build(passPhrase);
		try {
			privateKey = pgpSecKey.extractPrivateKey(pBESecretKeyDecryptor);
		} catch (PGPException e) {
			throw new PGPException("invalid privateKey passPhrase: " + String.valueOf(passPhrase), e);
		}
		return privateKey;
	}
	// --- <<IS-END-SHARED>> ---
}

