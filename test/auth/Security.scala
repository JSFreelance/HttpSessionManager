package auth

import java.util.Base64

object Security
{

  def EncodeAuthHeaders(user: String, password: String): String =
  {
    "Basic " + Base64.getEncoder.encodeToString(s"${user}:${password}".getBytes)
  }

}
